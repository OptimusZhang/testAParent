package blobAndText;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import shopping.util.JdbcUtil;

/**
 * @author jianwu.zhang
 * Blob是可变的二进制类型，
 * 可以把二进制数据，如音频，视频，存储到数据库中。
 * 应该使用PrepareStatment不使用Statement
 * 因为：Statement要书写静态的SQL，而静态的SQL不能表示BLOB格式。
 * 
 * TEXT
 * 接和传递都是使用STRING类型
 */
public class BlobAndTextTest {

	@Test
	public void testWriteBlob() throws Exception {
		Connection conn = JdbcUtil.INSTANCE.getConn();
		String sql = "INSERT INTO images(img) VALUES(?)";
		PreparedStatement pStatement = conn.prepareStatement(sql);
		InputStream is = new FileInputStream("C:\\Users\\jianwu.zhang\\Pictures\\book cover\\cover_nodebeginer.png");
		pStatement.setBlob(1, is);
		pStatement.executeUpdate();
		JdbcUtil.INSTANCE.close(conn, pStatement, null);
	}
	
	@Test
	public void testReadBlob() throws Exception {
		Connection conn = JdbcUtil.INSTANCE.getConn();
		String sql = "SELECT * FROM images WHERE id= ?";
		PreparedStatement pStatement = conn.prepareStatement(sql);
		pStatement.setInt(1, 2);
		ResultSet rs = pStatement.executeQuery();
		if (rs.next()) {
			Blob blob = rs.getBlob("img");
			
			//从数据库中取出BLOB类型的数据
			InputStream is = blob.getBinaryStream();
			//将输入流保存到磁盘文件中：文件的拷贝操作。
			Files.copy(is, Paths.get("C:\\TEMP\\123.png"), StandardCopyOption.REPLACE_EXISTING);
		}
		JdbcUtil.INSTANCE.close(conn, pStatement, null);
	}	
}
