package com.anda.mybatis.pagehelper;

import com.anda.mapper.TbItemMapper;
import com.anda.pojo.TbItem;
import com.anda.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPageHelper {

    @Test
    public void testPageHelper(){

        //初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        // 从容器中获得mapper代理对象
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);

        // 执行查询
        /*  为什么我会对这里很好奇，因为我从控制台得出的查询语句是：
          select id, title, sell_point, price, num, barcode, image, cid, status, created, updated
          from tb_item LIMIT 30 （由于是第一页，所以不是这样的 LIMIT 30,30(第二页) 、 LIMIT 60,30(第三页) ）
          而这里又是对所有的记录查询（List<TbItem> list = itemMapper.selectByExample(example);）

          实际上是这样的：
            由于PageHelper拦截了sql语句，所以，在execute之前，PageHelper做了几件事：

            DEBUG [com.anda.mapper.TbItemMapper.selectByExample_COUNT] ==>  Preparing: SELECT count(0) FROM tb_item
            DEBUG [com.anda.mapper.TbItemMapper.selectByExample_COUNT] ==> Parameters:
            DEBUG [com.anda.mapper.TbItemMapper.selectByExample_COUNT] <==      Total: 1
            //查询所有记录（总记录数用来计算分页，因为分页我们只传了两个参数，第几页和每页数，所以需要总页数来计算分多少页），
            //查询结果Total表示结果条数（1条嘛，比如总记录数是1500，1500只是一条结果）

            DEBUG [com.anda.mapper.TbItemMapper.selectByExample] ==>  Preparing:
            select id, title, sell_point, price, num, barcode, image, cid, status, created, updated
            from tb_item LIMIT 30
            DEBUG [com.anda.mapper.TbItemMapper.selectByExample] ==> Parameters:
            DEBUG [com.anda.mapper.TbItemMapper.selectByExample] <==      Total: 30
            //对拦截的sql语句加入了limit，只查询第几页的数据，结果集返回了30条记录（List）

            最后所有的分页信息存储在PageInfo类中，PageInfo.getList()可以取出结果，
            但是一般结果List要匹配前端的框架json格式，所以一般需要自己重新定义一个pojo类来存储格式化数据
            （数据传输要实现serializable接口，）

            //获取总记录数
             PageInfo<TbItem> pageInfo = new PageInfo(tbItems);
             result.setTotal(pageInfo.getTotal());
         */

        TbItemExample example = new TbItemExample();


        // 查询之前进行分页处理
        PageHelper.startPage(1, 30); // 注意，分页插件仅对最近的这一次查询有效，下一次查询就无效了。怎么实现的呢？使用了LocalThread。
        List<TbItem> list = itemMapper.selectByExample(example);
        // 取分页信息
        System.out.println("结果集中的记录数：" + list.size());
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        System.out.println("总记录数：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());

    }

}
