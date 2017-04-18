package one.to.one;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class JMSConsumer_1 {

	/**
	 * 消息消费者
	 * 接收信息的第二种方式：使用监听
	 */
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;//默认的连接用户名
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;//默认密码
	private static final String BROKERURL=ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址
	private static final int SENDNUM=10;//发送消息数量
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		ConnectionFactory connectionFactory;//创建连接工厂
		Connection connection=null;//连接
		Session session;//会话接收或者发送消息的线程
		Destination destination;//消息的目的地
		MessageConsumer messageConsumer;//消息消费者
		//实例化连接工厂
		connectionFactory=new ActiveMQConnectionFactory(JMSConsumer_1.USERNAME,JMSConsumer_1.PASSWORD,JMSConsumer_1.BROKERURL);
		
		try {
			//通过连接工厂获取连接
			connection=connectionFactory.createConnection();
			//创建连接
			connection.start();
			//创建session
			session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			//创建连接的消息队列
			destination=session.createQueue("第一个消息队列");
			//创建消息消费者
			messageConsumer=session.createConsumer(destination);
			//接收方式二：注册消息监听
			messageConsumer.setMessageListener(new Listener());
		} catch (JMSException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
