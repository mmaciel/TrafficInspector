package DDSTopics;

// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class MessageStructureTopic {
  
  // instance variables
  public byte[] message;
  
  // constructors
  public MessageStructureTopic() {}
  public MessageStructureTopic( byte[] __f1 ) {
    message = __f1;
  }
  
  public void clear() {
    message = null;
  }
  
  public void copy( MessageStructureTopic from ) {
    this.message = from.message;
  }
  
}; // MessageStructureTopic
