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
	 * ����һ��JMS֮activeMQ������
	 * ��Ϣ������
	 */
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;//Ĭ�ϵ������û���
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;//Ĭ������
	private static final String BROKERURL=ActiveMQConnection.DEFAULT_BROKER_URL;//Ĭ�����ӵ�ַ
	private static final int SENDNUM=10;//������Ϣ����
	public static void main(String[] args) throws Exception {
		// TODO �Զ����ɵķ������
		ConnectionFactory connectionFactory;//�������ӹ���
		Connection connection=null;//����
		Session session;//�Ự���ջ��߷�����Ϣ���߳�
		Destination destination;//��Ϣ��Ŀ�ĵ�
		MessageProducer messageProducer;//��Ϣ������
		//ʵ�������ӹ���
		connectionFactory=new ActiveMQConnectionFactory(JMSProducer.USERNAME,JMSProducer.PASSWORD,JMSProducer.BROKERURL);
		
		try {
			//ͨ�����ӹ�����ȡ����
			connection=connectionFactory.createConnection();
			//��������
			connection.start();
			session=connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);//�Ự�Զ�ȷ���յ���Ϣ
			//������Ϣ����
			destination=session.createQueue("��һ����Ϣ����");
			//������Ϣ������
			messageProducer=session.createProducer(destination);
			//������Ϣ
			sendMessage(session, messageProducer);
			//�ύ����
			session.commit();
		} catch (JMSException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally{
			if(connection!=null){
				connection.close();
			}
		}
	}
	//���ڷ���Ϣ�ķ���
	public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
			for(int i=0;i<JMSProducer.SENDNUM;i++){
				TextMessage message=session.createTextMessage("ActiveMQ ������Ϣ"+i);
				System.out.println("������Ϣ��"+"ActiveMQ���͵���Ϣ"+i);
				messageProducer.send(message);
			}
	}
}
