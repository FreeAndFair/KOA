import java.io.Serializable;

/**
 * This class encapsulates all information pertaining to a particular topic.
 * Each instance of the class will contain all data associated with a particular topic.
 */
public class Ballot implements Serializable {
    static final long serialVersionUID = -4800352941478282959L;
    private String topic;
    private int idNum;
    private byte[] image;
    private int sms;
    private long telNum;

    /**
     * Default constructor.
     */
    public Ballot(){}
    
    /**
     * Constructor with arguments.
     * @param topic The topic string.
     * @param idNum Unique ID of the topic.
     * @param image Image associated with the topic as a byte array.
     * @param sms SMS flag (1 or 0) indicating whether the vote is to be relayed.
     * @param telNum The number for which the vote should be relayed.
     */
    Ballot(String topic, int idNum, byte[] image, int sms, long telNum) {
       this.topic= topic;
       this.idNum = idNum;
       this.image=image;
       this.sms=sms;
       this.telNum=telNum;
    }
    
    /**
     * Getter method.
     * @return The topic string.
     */
    public String getTopic() {
        return topic;
    }
    
    /**
     * Setter method.
     * @param topic New string for which the topic should be set.
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    /**
     * Getter method.
     * @return Topic ID number.
     */
    public int getIdNum() {
        return idNum;
    }
    
    /**
     * Setter method.
     * @param idNum Set a new ID for the topic
     */
    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }
    
    /**
     * Getter method.
     * @return The image, as a byte array.
     */
    public byte[] getImage() {
        return image;
    }
    
    /**
     * Setter method.
     * @param image New byte array representing the new image.
     */
    public void setImage(byte[] image) {
        this.image = image;
    }
    
    /**
     * Getter method.
     * @return SMS flag integer.
     */
    public int getSMS() {
        return sms;
    }
    
    /**
     * Setter method.
     * @param sms New integer indicating whether the topic is to be relayed.
     */
    public void setSMS(int sms) {
    	this.sms = sms;
    }
    
    /**
     * Getter method.
     * @return The telephone number for which to relay the vote.
     */
    public long getTelNum() {
        return telNum;
    }
    
    /**
     * Setter method.
     * @param telNum New telephone number to associate with the topic.
     */
    public void setTelNum(long telNum) {
    	this.telNum = telNum;
    }
}