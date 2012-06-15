package DDSTopics;


// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.
import com.toc.coredx.DDS.*;
import java.nio.*;


public final class MessageStructureTopicTypeSupport implements TypeSupport{
  
  public ReturnCode_t register_type(DomainParticipant dp, String type_name) {
    if (dp.check_version( "3", "4", "0_p9" ) != 0) {
      System.out.println( "WARNING: MessageStructureTopic TypeSupport version does not match CoreDX Library version.");
      System.out.println( "This may cause software instability or crashes.");
    }
    return dp.register_type(this, type_name);
  }

  public String get_type_name()   { return "MessageStructureTopic"; }
  public long   getCTypeSupport() { return cTypeSupport; }

  public MessageStructureTopicTypeSupport() {
    MessageStructureTopic tmp = new MessageStructureTopic();
    cTypeSupport = DomainParticipant.createTypeSupport(this, 
                     getClass().getName(), tmp.getClass().getName());
  }

  // ------------------------------------------------------
  // implementation

  public DataReader   create_datareader(Subscriber sub, TopicDescription td, 
                                        SWIGTYPE_p__DataReader jni_dr) {
    return new MessageStructureTopicDataReader(sub, td, jni_dr);
  }
  public DataWriter   create_datawriter(Publisher  pub, Topic topic, SWIGTYPE_p__DataWriter jni_dw ) {
    return new MessageStructureTopicDataWriter(pub, topic, jni_dw);
  }

  // marshal variants
  public int     marshall ( ByteBuffer out_stream,  MessageStructureTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      size += 4; // for CDR 'header' 
      // src.message
      // src.message.length
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      if (src.message != null) {
        for (int i1 = 0; i1 < src.message.length; i1++ ) {
          // src.message[i1]
          size += 1;
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
      
      // src.message
      // src.message.length
      while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
      out_stream.putInt(src.message.length);
      for (int i1 = 0; i1 < src.message.length; i1++ ) {
          // src.message[i1]
          out_stream.put((byte)src.message[i1]);
      }
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_fixed_size (  ) { return 0; }
  public int     marshall_key ( ByteBuffer out_stream,  MessageStructureTopic src ) {
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

  public int     marshall_key_hash ( ByteBuffer out_stream,  MessageStructureTopic src ) {
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
  public int     unmarshall ( MessageStructureTopic t, ByteBuffer data, int s )    {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
      // t.message
      // t.message.length
      while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
      int    slen1 = data.getInt();
      t.message = new byte[slen1];
      for (int i1 = 0; i1 < t.message.length; i1++ ) {
        // t.message[i1]
        t.message[i1] = data.get();
      }
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key( MessageStructureTopic t, ByteBuffer data, int s ) {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key_hash( MessageStructureTopic t, ByteBuffer data, int s ) {
    data.order(ByteOrder.BIG_ENDIAN);
    return 0;
  }

  public int gen_typecode( ByteBuffer b ) {
    byte[] tc_data = { 
(byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x4c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'M', (byte)'e', (byte)'s', (byte)'s', (byte)'a', (byte)'g', (byte)'e', (byte)'S', (byte)'t', (byte)'r', (byte)'u', (byte)'c', (byte)'t', (byte)'u', (byte)'r', (byte)'e', (byte)'T', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x28, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'m', (byte)'e', (byte)'s', (byte)'s', (byte)'a', (byte)'g', (byte)'e', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,     };
    if (b != null) b.put(tc_data, 0, tc_data.length);
    return tc_data.length;
  }

  public int get_typecode_enc( ) {
    return (1 & 0x01);
  }

  // key field operations
  public boolean has_key (  ) { return false; }

  // <type> operations
  public MessageStructureTopic         alloc ()              { return new MessageStructureTopic(); }
  public void       clear (MessageStructureTopic instance)   { instance.clear(); }
  public void       destroy (MessageStructureTopic instance) { /* noop */ }
  public void       copy (MessageStructureTopic to, MessageStructureTopic from) { to.copy(from); }
  public boolean    get_field( String fieldname, CoreDX_FieldDef fdef ) { 
    return false;
  }
  private long cTypeSupport = 0;
}; // MessageStructureTopic
