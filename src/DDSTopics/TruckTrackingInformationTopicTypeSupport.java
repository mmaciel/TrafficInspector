package DDSTopics;


// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.
import com.toc.coredx.DDS.*;
import java.nio.*;


public final class TruckTrackingInformationTopicTypeSupport implements TypeSupport{
  
  public ReturnCode_t register_type(DomainParticipant dp, String type_name) {
    if (dp.check_version( "3", "4", "0_p9" ) != 0) {
      System.out.println( "WARNING: TruckTrackingInformationTopic TypeSupport version does not match CoreDX Library version.");
      System.out.println( "This may cause software instability or crashes.");
    }
    return dp.register_type(this, type_name);
  }

  public String get_type_name()   { return "TruckTrackingInformationTopic"; }
  public long   getCTypeSupport() { return cTypeSupport; }

  public TruckTrackingInformationTopicTypeSupport() {
    TruckTrackingInformationTopic tmp = new TruckTrackingInformationTopic();
    cTypeSupport = DomainParticipant.createTypeSupport(this, 
                     getClass().getName(), tmp.getClass().getName());
  }

  // ------------------------------------------------------
  // implementation

  public DataReader   create_datareader(Subscriber sub, TopicDescription td, 
                                        SWIGTYPE_p__DataReader jni_dr) {
    return new TruckTrackingInformationTopicDataReader(sub, td, jni_dr);
  }
  public DataWriter   create_datawriter(Publisher  pub, Topic topic, SWIGTYPE_p__DataWriter jni_dw ) {
    return new TruckTrackingInformationTopicDataWriter(pub, topic, jni_dw);
  }

  // marshal variants
  public int     marshall ( ByteBuffer out_stream,  TruckTrackingInformationTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      size += 4; // for CDR 'header' 
      // src.longitude
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.latitude
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.speed
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      // src.time
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
    } else {
      out_stream.clear();
      if ((1 & 0x01)==0) out_stream.order(ByteOrder.BIG_ENDIAN);
      else               out_stream.order(ByteOrder.LITTLE_ENDIAN);
      
      // add CDR 'header' 
      out_stream.put((byte)0x00);
      out_stream.put((byte)1);
      out_stream.put((byte)0x00); // flags
      out_stream.put((byte)0x00); // flags
      
      // src.longitude
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putDouble(src.longitude);
      // src.latitude
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putDouble(src.latitude);
      // src.speed
      while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
      out_stream.putFloat(src.speed);
      // src.time
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.time);
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_fixed_size (  ) { return 40; }
  public int     marshall_key ( ByteBuffer out_stream,  TruckTrackingInformationTopic src ) {
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

  public int     marshall_key_hash ( ByteBuffer out_stream,  TruckTrackingInformationTopic src ) {
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
  public int     unmarshall ( TruckTrackingInformationTopic t, ByteBuffer data, int s )    {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
      // t.longitude
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.longitude = data.getDouble();
      // t.latitude
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.latitude = data.getDouble();
      // t.speed
      while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
      t.speed = data.getFloat();
      // t.time
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.time = data.getLong();
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key( TruckTrackingInformationTopic t, ByteBuffer data, int s ) {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key_hash( TruckTrackingInformationTopic t, ByteBuffer data, int s ) {
    data.order(ByteOrder.BIG_ENDIAN);
    return 0;
  }

  public int gen_typecode( ByteBuffer b ) {
    byte[] tc_data = { 
(byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x94, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'T', (byte)'r', (byte)'u', (byte)'c', (byte)'k', (byte)'T', (byte)'r', (byte)'a', (byte)'c', (byte)'k', (byte)'i', (byte)'n', (byte)'g', (byte)'I', (byte)'n', (byte)'f', (byte)'o', (byte)'r', (byte)'m', (byte)'a', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)'T', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'l', (byte)'o', (byte)'n', (byte)'g', (byte)'i', (byte)'t', (byte)'u', (byte)'d', (byte)'e', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1a, (byte)0x00, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'l', (byte)'a', (byte)'t', (byte)'i', (byte)'t', (byte)'u', (byte)'d', (byte)'e', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'s', (byte)'p', (byte)'e', (byte)'e', (byte)'d', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'t', (byte)'i', (byte)'m', (byte)'e', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,     };
    if (b != null) b.put(tc_data, 0, tc_data.length);
    return tc_data.length;
  }

  public int get_typecode_enc( ) {
    return (1 & 0x01);
  }

  // key field operations
  public boolean has_key (  ) { return false; }

  // <type> operations
  public TruckTrackingInformationTopic         alloc ()              { return new TruckTrackingInformationTopic(); }
  public void       clear (TruckTrackingInformationTopic instance)   { instance.clear(); }
  public void       destroy (TruckTrackingInformationTopic instance) { /* noop */ }
  public void       copy (TruckTrackingInformationTopic to, TruckTrackingInformationTopic from) { to.copy(from); }
  public boolean    get_field( String fieldname, CoreDX_FieldDef fdef ) { 
    return false;
  }
  private long cTypeSupport = 0;
}; // TruckTrackingInformationTopic
