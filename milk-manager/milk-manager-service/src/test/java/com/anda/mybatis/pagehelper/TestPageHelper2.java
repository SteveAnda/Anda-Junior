package com.anda.mybatis.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.anda.mapper.TbItemMapper;
import com.anda.pojo.TbItem;
import com.anda.pojo.TbItemExample;

public class TestPageHelper2 {

    /**
     * @throws Exception
     * 2017年8月17日12:00:57 
     */
    @Test
    public void testPageHelper() throws Exception{

        //1、获得mapper代理对象
        //初始化一个spring容器
        ApplicationContext   applicationContext = null;
        try{
            //获得spring上下文对象
            applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        //拿到一个代理对象  我们要操作的商品信息 在mapper映射类中，我们通过上下文对象拿到这个类的代理
        TbItemMapper bean = applicationContext.getBean(TbItemMapper.class);
        //2、设置分页处理
        PageHelper.startPage(1, 20);//每页显示20条 相当于  SELECT * FROM taotao.tb_item limit 0,20;
        //3、执行查询
        TbItemExample example = new TbItemExample();
        //Criteria criteria = example.createCriteria();
        //criteria.andIdEqualTo(value) //这个是根据某个条件查 比如主键商品ID
        List<TbItem> list = bean.selectByExample(example);//example不设置 表示无条件 这个时候bean已经将分页效果（sql语句）作用在example上了

        if(list != null & list.size()>0){
            int i = 0;
            for(TbItem item : list){
                System.out.println(item.getTitle()+","+(i+1));//输出商品的标题，一页20行
                i++;
            }
        }
        //4、取分页后的结果
        //包装list
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();//总记录数
        System.out.println("total:"+total);
        int pages = pageInfo.getPages();
        System.out.println("pages:"+pages);//总页数
        int pageSize= pageInfo.getPageSize();
        System.out.println("pageSize:"+pageSize);//每页的展示数

    }

}