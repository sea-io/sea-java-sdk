public class Block implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int index;
	
	private String hash;
	
	private String previousHash;
	
	private long timestamp;
	
	private int nonce;

	private List<Transaction> transactions;
  
  }
