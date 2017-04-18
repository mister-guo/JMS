package one.to.one;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;




public class JMSProducer {
	/**
	 * 这是一个JMS之activeMQ的例子
	 * 消息生产者
	 */
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;//默认的连接用户名
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;//默认密码
	private static final String BROKERURL=ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址
	private static final int SENDNUM=10;//发送消息数量
	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		ConnectionFactory connectionFactory;//创建连接工厂
		Connection connection=null;//连接
		Session session;//会话接收或者发送消息的线程
		Destination destination;//消息的目的地
		MessageProducer messageProducer;//消息生产者
		//实例化连接工厂
		connectionFactory=new ActiveMQConnectionFactory(JMSProducer.USERNAME,JMSProducer.PASSWORD,JMSProducer.BROKERURL);
		
		try {
			//通过连接工厂获取连接
			connection=connectionFactory.createConnection();
			//创建连接
			connection.start();
			session=connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);//会话自动确认收到消息
			//创建消息队列
			destination=session.createQueue("第一个消息队列");
			//创建消息生产者
			messageProducer=session.createProducer(destination);
			//发送消息
			sendMessage(session, messageProducer);
			//提交事务
			session.commit();
		} catch (JMSException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			if(connection!=null){
				connection.close();
			}
		}
	}
	//用于发消息的方法
	public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
			for(int i=0;i<JMSProducer.SENDNUM;i++){
				TextMessage message=session.createTextMessage("ActiveMQ 发送消息"+i);
				System.out.println("发送消息："+"ActiveMQ发送的信息"+i);
				messageProducer.send(message);
			}
	}
}
