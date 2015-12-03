package cn.bblink.monitor.util;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.SortedMap;
/**
 * Created by lwb on 2014/10/31.
 */
public class SqlService {
    private String driver="com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://192.168.0.173:3306/bblink_monitor";
    private String username="root";
    private String password ="bblink2014$";

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public Connection getConnection(){
        try {
            Class.forName(driver);
            conn= DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void close(){
        try {
            if (rs!=null){
                rs.close();
            }
            if (ps!=null){
                ps.close();
            }
            if (conn!=null){
                conn.close();
            }

        }catch (Exception e){}
    }

    public SortedMap[] query(String sql,Object[] params){
        SortedMap[] sms=null;
        getConnection();
        try {
            ps=conn.prepareStatement(sql);
            if (params!=null){
                for (int i=0;i<params.length;i++){
                    ps.setObject(i+1,params[i]);
                }
            }
            rs=ps.executeQuery();
            Result result = ResultSupport.toResult(rs);
            sms = result.getRows();



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return sms;
    }

    public void update(String sql,Object[] params){
        getConnection();
        try {
            ps=conn.prepareStatement(sql);
            if (params!=null){
                for (int i=0;i<params.length;i++){
                    ps.setObject(i+1,params[i]);
                }
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }

    }
}
