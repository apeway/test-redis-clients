package org.liws.redis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

@SuppressWarnings({ "unchecked", "resource" })
public class T_Jedis {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
		final RedisTemplate<String, Object> redisTemplate = appCtx.getBean("redisTemplate", RedisTemplate.class);
		
		// opsForValue
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		value.set("lp", "hello word");
		System.out.println(value.get("lp"));
		// opsForHash
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "lp");
		map.put("age", "26");
		hash.putAll("lpMap", map);
		System.out.println(hash.entries("lpMap"));
		// opsForList
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush("lpList", "lp");
		list.rightPush("lpList", "26");
		System.out.println(list.range("lpList", 0, 1));
		// opsForSet
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		set.add("lpSet", "lp");
		set.add("lpSet", "26");
		set.add("lpSet", "178cm");
		System.out.println(set.members("lpSet"));
		// opsForZSet
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		zset.add("lpZset", "lp", 0);
		zset.add("lpZset", "26", 1);
		zset.add("lpZset", "178cm", 2);
		System.out.println(zset.rangeByScore("lpZset", 0, 2));
	}
	
	@Test
	public void testStringAndCommon() throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");
		
//		redisUtil.set("name", "用友");
//		System.out.println("hasKey('name'): " + redisUtil.hasKey("name"));
//		System.out.println("get('name'): " + redisUtil.get("name"));
//		redisUtil.del("name");
//		System.out.println("hasKey('name'): " + redisUtil.hasKey("name"));
		
		redisUtil.set("age", 20, 2);
		System.out.println("getExpire('age'): " + redisUtil.getExpire("age"));	// expire('age'): 2
		Thread.sleep(500);
		System.out.println("get('age'): " + redisUtil.get("age"));				// get('age'): 20
		Thread.sleep(2000);
		System.out.println("get('age'): " + redisUtil.get("age"));				// get('age'): null
			
		// incr和decr测试不过???
//		redisUtil.set("speed", 200);
//		System.out.println(redisUtil.get("speed"));
//		try {
//			System.out.println(redisUtil.incr("speed", 10));;
//			System.out.println(redisUtil.get("speed"));
//			System.out.println(redisUtil.decr("speed", 20));;
//			System.out.println(redisUtil.get("speed"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Test
	public void testHash() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");
		
		final String myHashKey = "myhash";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "yonyou");
		map.put("age", 20);
		map.put("address", "beijing");
		redisUtil.hmset(myHashKey, map);
		System.out.println(redisUtil.hmget(myHashKey));
		
		redisUtil.hset(myHashKey, "email", "xx@yonyou.com");
		redisUtil.hset(myHashKey, "age", "100");
		System.out.println(redisUtil.hmget(myHashKey));
		
		System.out.println(redisUtil.hHasKey(myHashKey, "age") + ", " + redisUtil.hget(myHashKey, "age"));
		redisUtil.hdel(myHashKey, "age");
		System.out.println(redisUtil.hHasKey(myHashKey, "age"));
		
		// hincr、hdecr
	}
	
		@Test
	public void test1() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");
	       
	     //=====================testString======================  
	     //redisUtil.set("name", "王赛超");  
	     //redisUtil.set("age", 24);  
	     //redisUtil.set("address", "河北邯郸");  
	       
	     //System.out.println(redisUtil.set("address", "河北邯郸", 50));  
	       
	     //System.out.println(redisUtil.get("age"));  
	       
	       
	     //redisUtil.set("age", 1000);  
	       
	     //Object object = redisUtil.get("user2");  
	       
	     //System.out.println(object);  
	       
	     //redisUtil.del("address");  
	     //redisUtil.set("class", 15);  
	     //long incr = redisUtil.incr("a", 1);  
	     //System.out.println(incr);  
	       
	     //Thread.sleep(5000);  
	    
		
	     //System.out.println(redisUtil.sSetAndTime("15532002727",1000,"haha"));  
	     //System.out.println(redisUtil.sGet("15532002727"));  
	     //System.out.println(redisUtil.sHasKey("15532002727","name"));  
		
	     System.out.println(redisUtil.lRemove("15532002728",1,2));  
	     System.out.println(redisUtil.lGet("15532002728",0,-1));  
	     System.out.println(redisUtil.lGetListSize("15532002728"));  
	     System.out.println(redisUtil.lGetIndex("15532002728",1));  
	       
	       
	     //System.out.println(redisUtil.getExpire("15532002725"));  
	       
	     //System.out.println(redisUtil.hget("15532002725","name"));  
	     //System.out.println(redisUtil.hmget("15532002725"));  
	}
	 
}
