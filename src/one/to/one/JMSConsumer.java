package one.to.one;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class JMSConsumer {

	/**
	 * ��Ϣ������
	 */
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;//Ĭ�ϵ������û���
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;//Ĭ������
	private static final String BROKERURL=ActiveMQConnection.DEFAULT_BROKER_URL;//Ĭ�����ӵ�ַ
	private static final int SENDNUM=10;//������Ϣ����
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		ConnectionFactory connectionFactory;//�������ӹ���
		Connection connection=null;//����
		Session session;//�Ự���ջ��߷�����Ϣ���߳�
		Destination destination;//��Ϣ��Ŀ�ĵ�
		MessageConsumer messageConsumer;//��Ϣ������
		//ʵ�������ӹ���
		connectionFactory=new ActiveMQConnectionFactory(JMSConsumer.USERNAME,JMSConsumer.PASSWORD,JMSConsumer.BROKERURL);
		
		try {
			//ͨ�����ӹ�����ȡ����
			connection=connectionFactory.createConnection();
			//��������
			connection.start();
			//����session
			session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			//�������ӵ���Ϣ����
			destination=session.createQueue("��һ����Ϣ����");
			//������Ϣ������
			messageConsumer=session.createConsumer(destination);
			//���շ�ʽһ��
			while(true){
				TextMessage textMessage=(TextMessage) messageConsumer.receive(1000);//ÿһ�����һ��
				if(textMessage!=null){
					System.out.println("�յ�����Ϣ"+textMessage.getText());
				}else{
					break;
				}
			}
		} catch (JMSException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}

}
