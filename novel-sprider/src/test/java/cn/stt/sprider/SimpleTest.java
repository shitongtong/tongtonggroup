package cn.stt.sprider;

import cn.stt.sprider.db.DBPool;
import cn.stt.sprider.db.YiQueryRunner;
import cn.stt.sprider.entity.NovelEntity;
import cn.stt.sprider.utils.StringUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016-11-15.
 */
public class SimpleTest {
    private static final String JDBC_FILE = "jdbc.properties";

    /*static {
        System.out.println(JDBC_FILE);
    }*/

    public static void main(String[] args) {
        Long a = 1128L;
//        Long b = 12855L;
        long b = 1128l;
        if (a >= b){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }



    @Test
    public void testConn() throws SQLException {
        Connection conn = DBPool.getInstance().getDruidConnection();
        YiQueryRunner queryRunner = new YiQueryRunner(true);
        String sql = "INSERT INTO t_article(articlename, pinyin, initial ,keywords ,authorid ,author ,category ,subcategory, intro ,fullflag ,postdate,dayvisit, weekvisit, monthvisit,  allvisit, dayvote, weekvote, monthvote, allvote ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        Integer isFull = null;
        NovelEntity novel = new NovelEntity();
        if (novel.getFullFlag() != null && novel.getFullFlag()){
            isFull = 1;
        }else{
            isFull = 0;
        }
        Object[] params = new Object[]{novel.getNovelName(), novel.getPinyin(), novel.getInitial(), StringUtil.trimToEmpty(novel.getKeywords()), Integer.valueOf(0), novel.getAuthor(), novel.getTopCategory(), novel.getSubCategory(), novel.getIntro(), isFull, new Timestamp(System.currentTimeMillis()), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)};
        Number b = (Number)queryRunner.save(conn, sql, params);

    }

    @Test
    public void execCmd(){
        StringBuilder commands = new StringBuilder();
        commands.append("D:\\softwareInstallPackageDir\\jad158g.win\\jad.exe ");
        commands.append("-r -ff -sjava -dD:\\project_git\\tongtonggroup\\novel-web\\src\\main\\java ");
        commands.append("D:\\developExample\\YiDuNovel-V1.1.8Beta\\YiDuNovel\\WEB-INF\\classes/**/*.class");
        try {
            Runtime.getRuntime().exec(commands.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void copyFile() throws IOException {
        String srcDir = "D:\\developExample\\YiDuNovel-V1.1.8Beta\\YiDuNovel\\WEB-INF\\classes\\org\\yidu\\novel\\action\\base";
        String desDir = "D:\\project_git\\tongtonggroup\\novel-web\\src\\main\\java\\org\\yidu\\novel\\action\\base";
        copyFolder(srcDir,desDir);
    }

    private void copyFolder(String srcDir, String desDir){
        File srcFile = new File(srcDir);
        File desFile = new File(desDir);
        if (!desFile.exists()){
            desFile.mkdirs();
        }
        if (srcFile.exists()){
            String[] list = srcFile.list();
            for (String filePath : list){
                File temp = null;
                if(srcDir.endsWith(File.separator)){
                    temp=new File(srcDir+filePath);
                }
                else{
                    temp=new File(srcDir+File.separator+filePath);
                }
                if (temp.isFile()){
                    System.out.println("file is file");
                    InputStream is = null;
                    OutputStream os = null;
                    try{
                        is = new FileInputStream(temp);
                        String newFileName = desDir+File.separator+temp.getName().substring(0,temp.getName().indexOf("."))+".java";
//                        String newFileName = desDir+"/"+temp.getName();
                        os = new FileOutputStream(newFileName);
                        byte[] b = new byte[1024];
                        int len;
                        while((len = is.read(b))!=-1){
                            os.write(b,0,len);
                            os.flush();
                        }
                        os.close();
                        is.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }finally {
                        if (os != null){
                            try {
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (is != null){
                            try {
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }else if(temp.isDirectory()){
                    System.out.println("file is dir");
                    copyFolder(srcDir+"/"+filePath,desDir+"/"+filePath);
                }else{
                    System.out.println("未知情况："+temp.getName());
                }
            }
        }

    }
}
