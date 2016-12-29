package dataservice.serviceimpl;

import dataservice.ServiceBase;
import dataservice.entity.Fin;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Y on 2016/12/22.
 */
public class FinService extends ServiceBase<Fin> {
    private static FinService fs = null;
    /**
     * 单例的服务获取方法，供api中进行玩家信息的操作
     * @return FinService对象
     */
    public static FinService getFinService(){
        if(fs == null){
            fs = new FinService();
        }
        return fs;
    }

    private FinService() {
    }

    @Override
    public Fin findById(int id) {
        //获取链接
        Connection conn = getConnection();

        //mysql语句对象
        PreparedStatement findPS = null;

        //如传入的是无效id直接返回null
        if(id == 0){
            return null;
        }

        try {
            //对sql语句进行预编译
            findPS = conn.prepareStatement("SELECT * FROM fin WHERE id=?");

            //对sql变量赋值
            findPS.setInt(1,id);

            //取回结果集
            ResultSet rs = findPS.executeQuery();

            //如无结果则返回null
            if(!rs.next()){
                return null;
            }

            //新建结果实体
            Fin jg = new Fin();

            //使用结果集填充结果实体
            finDataSet(jg,rs);

            //关闭结果集及连接
            findPS.close();
            rs.close();

            //返回结果
            return jg;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            returnConnection(conn);
        }
        return null;
    }

    @Override
    public Fin add(Fin newpl) {
        //通过基类的getConnection方法获取链接对象
        Connection conn = getConnection();
        //新建sql语句对象
        PreparedStatement addPS = null;
        //新建结果集对象
        ResultSet rs = null;
        //用于保存插入完成后id值的变量
        int id = 0 ;

        try {
            //对mysql语句进行预编译
            addPS = conn.prepareStatement("INSERT INTO fin(cash,debt) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

            //填入参数字符串
            addPS.setInt(1,newpl.getCash());
            addPS.setInt(2,newpl.getDebt());

            //执行插入
            addPS.executeUpdate();

            //取回插入中生成键的结果集
            rs = addPS.getGeneratedKeys();

            //如插入失败返回空对象
            if(!rs.next()){
                return null;
            }

            //取出插入后数据在数据库id
            id = rs.getInt(1);

            //调用service中的按id查找 将实体拉出并返回
            newpl = findById(id);

            //关闭结果集及链接
            rs.close();
            addPS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //归还连接
            returnConnection(conn);
        }

        return newpl;
    }

    public boolean delete(int id) {
       return delete(id,"fin");
    }

    /**
     * 通过结果集将信息映射到实体
     * @param jg 需要填充的实体对象
     * @param rs 结果集
     * @throws SQLException
     */
    protected void finDataSet(Fin jg, ResultSet rs) throws SQLException{
        jg.setId(rs.getInt("id"));
        jg.setCash(rs.getInt("cash"));
        jg.setDebt(rs.getInt("debt"));
        jg.setFunds(rs.getInt("funds"));
        jg.setFundsRate(rs.getInt("funds_rate"));
        jg.setFixedDeposit(rs.getInt("fixed_deposit"));
        jg.setFixedDepositRate(rs.getInt("fixed_deposit_rate"));
        jg.setCurrentDeposit(rs.getInt("current_deposit"));
        jg.setCurrentDepositRate(rs.getInt("current_deposit_rate"));
        jg.setRent(rs.getInt("rent"));
    }
    public boolean update(Fin fin){
        //获取链接
        Connection conn = getConnection();

        //mysql语句对象
        PreparedStatement updatePS = null;
        try {
            //对sql语句进行预编译
            updatePS = conn.prepareStatement("UPDATE fin SET cash = ?, debt = ?, current_deposit = ?, current_deposit_rate = ? WHERE id=?");

            updatePS.setInt(1,fin.getCash());
            updatePS.setInt(2,fin.getDebt());
            updatePS.setInt(3,fin.getCurrentDeposit());
            updatePS.setInt(4,fin.getCurrentDepositRate());
            updatePS.setInt(5,fin.getId());

            updatePS.execute();
            //关闭结果集及连接
            updatePS.close();



            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            returnConnection(conn);
        }
        return false;
    }
    public ArrayList<Integer> rankList(){
        //获取链接
        Connection conn = getConnection();

        //mysql语句对象
        PreparedStatement queryPS = null;
        try {
            //对sql语句进行预编译
            queryPS = conn.prepareStatement("select id from fin order by (cash + current_deposit - debt) DESC LIMIT 10");

            ResultSet rs = queryPS.executeQuery();

            ArrayList<Integer> al = new ArrayList<>();

            while(rs.next()){
                al.add(rs.getInt("id"));
            }


            //关闭结果集及连接
            queryPS.close();



            return al;
        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            returnConnection(conn);
        }
        return null;
    }
}
