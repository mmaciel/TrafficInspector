
// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.
import com.toc.coredx.DDS.*;
import java.nio.*;


public final class ConnectionReportTopicTypeSupport implements TypeSupport{
  
  public ReturnCode_t register_type(DomainParticipant dp, String type_name) {
    if (dp.check_version( "3", "4", "0_p9" ) != 0) {
      System.out.println( "WARNING: ConnectionReportTopic TypeSupport version does not match CoreDX Library version.");
      System.out.println( "This may cause software instability or crashes.");
    }
    return dp.register_type(this, type_name);
  }

  public String get_type_name()   { return "ConnectionReportTopic"; }
  public long   getCTypeSupport() { return cTypeSupport; }

  public ConnectionReportTopicTypeSupport() {
    ConnectionReportTopic tmp = new ConnectionReportTopic();
    cTypeSupport = DomainParticipant.createTypeSupport(this, 
                     getClass().getName(), tmp.getClass().getName());
  }

  // ------------------------------------------------------
  // implementation

  public DataReader   create_datareader(Subscriber sub, TopicDescription td, 
                                        SWIGTYPE_p__DataReader jni_dr) {
    return new ConnectionReportTopicDataReader(sub, td, jni_dr);
  }
  public DataWriter   create_datawriter(Publisher  pub, Topic topic, SWIGTYPE_p__DataWriter jni_dw ) {
    return new ConnectionReportTopicDataWriter(pub, topic, jni_dw);
  }

  // marshal variants
  public int     marshall ( ByteBuffer out_stream,  ConnectionReportTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      size += 4; // for CDR 'header' 
      // src.leastSignificantBitsVehicleId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.mostSignificantBitsVehicleId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.leastSignificantBitsGatewayId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.mostSignificantBitsGatewayId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.connected
      size += 1;
    } else {
      out_stream.clear();
      if ((1 & 0x01)==0) out_stream.order(ByteOrder.BIG_ENDIAN);
      else               out_stream.order(ByteOrder.LITTLE_ENDIAN);
      
      // add CDR 'header' 
      out_stream.put((byte)0x00);
      out_stream.put((byte)1);
      out_stream.put((byte)0x00); // flags
      out_stream.put((byte)0x00); // flags
      
      // src.leastSignificantBitsVehicleId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.leastSignificantBitsVehicleId);
      // src.mostSignificantBitsVehicleId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.mostSignificantBitsVehicleId);
      // src.leastSignificantBitsGatewayId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.leastSignificantBitsGatewayId);
      // src.mostSignificantBitsGatewayId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.mostSignificantBitsGatewayId);
      // src.connected
      out_stream.put((byte)(src.connected==true?1:0));
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_fixed_size (  ) { return 41; }
  public int     marshall_key ( ByteBuffer out_stream,  ConnectionReportTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      size += 4; // for CDR 'header' 
      // src.leastSignificantBitsVehicleId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.mostSignificantBitsVehicleId
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
      
      // src.leastSignificantBitsVehicleId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.leastSignificantBitsVehicleId);
      // src.mostSignificantBitsVehicleId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.mostSignificantBitsVehicleId);
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_key_hash ( ByteBuffer out_stream,  ConnectionReportTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.leastSignificantBitsVehicleId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.mostSignificantBitsVehicleId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
    } else {
      out_stream.clear();
      out_stream.order(ByteOrder.BIG_ENDIAN);
      
      // src.leastSignificantBitsVehicleId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.leastSignificantBitsVehicleId);
      // src.mostSignificantBitsVehicleId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.mostSignificantBitsVehicleId);
      size = out_stream.position();
    }
    return size;
  }

  public boolean key_must_hash (  ) { return false; }

  // unmarshal variants
  public int     unmarshall ( ConnectionReportTopic t, ByteBuffer data, int s )    {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
      // t.leastSignificantBitsVehicleId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.leastSignificantBitsVehicleId = data.getLong();
      // t.mostSignificantBitsVehicleId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.mostSignificantBitsVehicleId = data.getLong();
      // t.leastSignificantBitsGatewayId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.leastSignificantBitsGatewayId = data.getLong();
      // t.mostSignificantBitsGatewayId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.mostSignificantBitsGatewayId = data.getLong();
      // t.connected
      t.connected = (data.get()==0?false:true);
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key( ConnectionReportTopic t, ByteBuffer data, int s ) {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
      // t.leastSignificantBitsVehicleId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.leastSignificantBitsVehicleId = data.getLong();
      // t.mostSignificantBitsVehicleId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.mostSignificantBitsVehicleId = data.getLong();
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key_hash( ConnectionReportTopic t, ByteBuffer data, int s ) {
    data.order(ByteOrder.BIG_ENDIAN);
    // t.leastSignificantBitsVehicleId
    while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
    t.leastSignificantBitsVehicleId = data.getLong();
    // t.mostSignificantBitsVehicleId
    while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
    t.mostSignificantBitsVehicleId = data.getLong();
    return 0;
  }

  public int gen_typecode( ByteBuffer b ) {
    byte[] tc_data = { 
(byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'C', (byte)'o', (byte)'n', (byte)'n', (byte)'e', (byte)'c', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)'R', (byte)'e', (byte)'p', (byte)'o', (byte)'r', (byte)'t', (byte)'T', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x30, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'l', (byte)'e', (byte)'a', (byte)'s', (byte)'t', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'n', (byte)'t', (byte)'B', (byte)'i', (byte)'t', (byte)'s', (byte)'V', (byte)'e', (byte)'h', (byte)'i', (byte)'c', (byte)'l', (byte)'e', (byte)'I', (byte)'d', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2e, (byte)0x00, (byte)0x1d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'m', (byte)'o', (byte)'s', (byte)'t', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'n', (byte)'t', (byte)'B', (byte)'i', (byte)'t', (byte)'s', (byte)'V', (byte)'e', (byte)'h', (byte)'i', (byte)'c', (byte)'l', (byte)'e', (byte)'I', (byte)'d', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2e, (byte)0x00, (byte)0x1e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'l', (byte)'e', (byte)'a', (byte)'s', (byte)'t', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'n', (byte)'t', (byte)'B', (byte)'i', (byte)'t', (byte)'s', (byte)'G', (byte)'a', (byte)'t', (byte)'e', (byte)'w', (byte)'a', (byte)'y', (byte)'I', (byte)'d', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2e, (byte)0x00, (byte)0x1d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'m', (byte)'o', (byte)'s', (byte)'t', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'n', (byte)'t', (byte)'B', (byte)'i', (byte)'t', (byte)'s', (byte)'G', (byte)'a', (byte)'t', (byte)'e', (byte)'w', (byte)'a', (byte)'y', (byte)'I', (byte)'d', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1a, (byte)0x00, (byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'c', (byte)'o', (byte)'n', (byte)'n', (byte)'e', (byte)'c', (byte)'t', (byte)'e', (byte)'d', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x07, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,     };
    if (b != null) b.put(tc_data, 0, tc_data.length);
    return tc_data.length;
  }

  public int get_typecode_enc( ) {
    return (1 & 0x01);
  }

  // key field operations
  public boolean has_key (  ) { return true; }

  // <type> operations
  public ConnectionReportTopic         alloc ()              { return new ConnectionReportTopic(); }
  public void       clear (ConnectionReportTopic instance)   { instance.clear(); }
  public void       destroy (ConnectionReportTopic instance) { /* noop */ }
  public void       copy (ConnectionReportTopic to, ConnectionReportTopic from) { to.copy(from); }
  public boolean    get_field( String fieldname, CoreDX_FieldDef fdef ) { 
    return false;
  }
  private long cTypeSupport = 0;
}; // ConnectionReportTopic
