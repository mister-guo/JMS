package one.to.many;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//消息监听
public class Listener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		// TODO 自动生成的方法存根
		try {
			System.out.println("收到的消息"+((TextMessage) message).getText());
		} catch (JMSException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
