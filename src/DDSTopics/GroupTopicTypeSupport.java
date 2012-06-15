package DDSTopics;


// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.
import com.toc.coredx.DDS.*;
import java.nio.*;


public final class GroupTopicTypeSupport implements TypeSupport{
  
  public ReturnCode_t register_type(DomainParticipant dp, String type_name) {
    if (dp.check_version( "3", "4", "0_p9" ) != 0) {
      System.out.println( "WARNING: GroupTopic TypeSupport version does not match CoreDX Library version.");
      System.out.println( "This may cause software instability or crashes.");
    }
    return dp.register_type(this, type_name);
  }

  public String get_type_name()   { return "GroupTopic"; }
  public long   getCTypeSupport() { return cTypeSupport; }

  public GroupTopicTypeSupport() {
    GroupTopic tmp = new GroupTopic();
    cTypeSupport = DomainParticipant.createTypeSupport(this, 
                     getClass().getName(), tmp.getClass().getName());
  }

  // ------------------------------------------------------
  // implementation

  public DataReader   create_datareader(Subscriber sub, TopicDescription td, 
                                        SWIGTYPE_p__DataReader jni_dr) {
    return new GroupTopicDataReader(sub, td, jni_dr);
  }
  public DataWriter   create_datawriter(Publisher  pub, Topic topic, SWIGTYPE_p__DataWriter jni_dw ) {
    return new GroupTopicDataWriter(pub, topic, jni_dw);
  }

  // marshal variants
  public int     marshall ( ByteBuffer out_stream,  GroupTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      size += 4; // for CDR 'header' 
      // src.groupType
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      // src.groupIDCollection
      // src.groupIDCollection.length
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      if (src.groupIDCollection != null) {
        for (int i2 = 0; i2 < src.groupIDCollection.length; i2++ ) {
          // src.groupIDCollection[i2]
          size = (size+3) & 0xfffffffc;// align 4
          size += 4;
        }
      }
    } else {
      out_stream.clear();
      if ((1 & 0x01)==0) out_stream.order(ByteOrder.BIG_ENDIAN);
      else               out_stream.order(ByteOrder.LITTLE_ENDIAN);
      
      // add CDR 'header' 
      out_stream.put((byte)0x00);
      out_stream.put((byte)1);
      out_stream.put((byte)0x00); // flags
      out_stream.put((byte)0x00); // flags
      
      // src.groupType
      while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
      out_stream.putInt(src.groupType);
      // src.groupIDCollection
      // src.groupIDCollection.length
      while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
      out_stream.putInt(src.groupIDCollection.length);
      for (int i2 = 0; i2 < src.groupIDCollection.length; i2++ ) {
          // src.groupIDCollection[i2]
          while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
          out_stream.putInt(src.groupIDCollection[i2]);
      }
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_fixed_size (  ) { return 0; }
  public int     marshall_key ( ByteBuffer out_stream,  GroupTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      size += 4; // for CDR 'header' 
    } else {
      out_stream.clear();
      if ((1 & 0x01)==0) out_stream.order(ByteOrder.BIG_ENDIAN);
      else               out_stream.order(ByteOrder.LITTLE_ENDIAN);
      
      // add CDR 'header' 
      out_stream.put((byte)0x00);
      out_stream.put((byte)1);
      out_stream.put((byte)0x00); // flags
      out_stream.put((byte)0x00); // flags
      
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_key_hash ( ByteBuffer out_stream,  GroupTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
    } else {
      out_stream.clear();
      out_stream.order(ByteOrder.BIG_ENDIAN);
      
      size = out_stream.position();
    }
    return size;
  }

  public boolean key_must_hash (  ) { return false; }

  // unmarshal variants
  public int     unmarshall ( GroupTopic t, ByteBuffer data, int s )    {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
      // t.groupType
      while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
      t.groupType = data.getInt();
      // t.groupIDCollection
      // t.groupIDCollection.length
      while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
      int    slen2 = data.getInt();
      t.groupIDCollection = new int[slen2];
      for (int i2 = 0; i2 < t.groupIDCollection.length; i2++ ) {
        // t.groupIDCollection[i2]
        while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
        t.groupIDCollection[i2] = data.getInt();
      }
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key( GroupTopic t, ByteBuffer data, int s ) {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key_hash( GroupTopic t, ByteBuffer data, int s ) {
    data.order(ByteOrder.BIG_ENDIAN);
    return 0;
  }

  public int gen_typecode( ByteBuffer b ) {
    byte[] tc_data = { 
(byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x64, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0b, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'G', (byte)'r', (byte)'o', (byte)'u', (byte)'p', (byte)'T', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)0x00, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'g', (byte)'r', (byte)'o', (byte)'u', (byte)'p', (byte)'T', (byte)'y', (byte)'p', (byte)'e', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2e, (byte)0x00, (byte)0x12, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'g', (byte)'r', (byte)'o', (byte)'u', (byte)'p', (byte)'I', (byte)'D', (byte)'C', (byte)'o', (byte)'l', (byte)'l', (byte)'e', (byte)'c', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,     };
    if (b != null) b.put(tc_data, 0, tc_data.length);
    return tc_data.length;
  }

  public int get_typecode_enc( ) {
    return (1 & 0x01);
  }

  // key field operations
  public boolean has_key (  ) { return false; }

  // <type> operations
  public GroupTopic         alloc ()              { return new GroupTopic(); }
  public void       clear (GroupTopic instance)   { instance.clear(); }
  public void       destroy (GroupTopic instance) { /* noop */ }
  public void       copy (GroupTopic to, GroupTopic from) { to.copy(from); }
  public boolean    get_field( String fieldname, CoreDX_FieldDef fdef ) { 
    return false;
  }
  private long cTypeSupport = 0;
}; // GroupTopic
