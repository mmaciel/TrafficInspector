package DDSTopics;

// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.



public class GroupTopic {
  
  // instance variables
  public int groupType;
  public int[] groupIDCollection;
  
  // constructors
  public GroupTopic() {}
  public GroupTopic( int __f1, int[] __f2 ) {
    groupType = __f1;
    groupIDCollection = __f2;
  }
  
  public void clear() {
    groupIDCollection = null;
  }
  
  public void copy( GroupTopic from ) {
    this.groupType = from.groupType;
    this.groupIDCollection = from.groupIDCollection;
  }
  
}; // GroupTopic
