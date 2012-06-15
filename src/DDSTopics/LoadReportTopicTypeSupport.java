package DDSTopics;


// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.
import com.toc.coredx.DDS.*;
import java.nio.*;


public final class LoadReportTopicTypeSupport implements TypeSupport{
  
  public ReturnCode_t register_type(DomainParticipant dp, String type_name) {
    if (dp.check_version( "3", "4", "0_p9" ) != 0) {
      System.out.println( "WARNING: LoadReportTopic TypeSupport version does not match CoreDX Library version.");
      System.out.println( "This may cause software instability or crashes.");
    }
    return dp.register_type(this, type_name);
  }

  public String get_type_name()   { return "LoadReportTopic"; }
  public long   getCTypeSupport() { return cTypeSupport; }

  public LoadReportTopicTypeSupport() {
    LoadReportTopic tmp = new LoadReportTopic();
    cTypeSupport = DomainParticipant.createTypeSupport(this, 
                     getClass().getName(), tmp.getClass().getName());
  }

  // ------------------------------------------------------
  // implementation

  public DataReader   create_datareader(Subscriber sub, TopicDescription td, 
                                        SWIGTYPE_p__DataReader jni_dr) {
    return new LoadReportTopicDataReader(sub, td, jni_dr);
  }
  public DataWriter   create_datawriter(Publisher  pub, Topic topic, SWIGTYPE_p__DataWriter jni_dw ) {
    return new LoadReportTopicDataWriter(pub, topic, jni_dw);
  }

  // marshal variants
  public int     marshall ( ByteBuffer out_stream,  LoadReportTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      size += 4; // for CDR 'header' 
      // src.leastSignificantBitsParticipantId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.mostSignificantBitsParticipantId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.participantType
      size += 1;
      // src.cpuUsage
      size += 1;
      // src.freeMemory
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      // src.numberOfConnectedVehicles
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      // src.participantIp
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      size += (src.participantIp==null)?1:(src.participantIp.length()+1);
    } else {
      out_stream.clear();
      if ((1 & 0x01)==0) out_stream.order(ByteOrder.BIG_ENDIAN);
      else               out_stream.order(ByteOrder.LITTLE_ENDIAN);
      
      // add CDR 'header' 
      out_stream.put((byte)0x00);
      out_stream.put((byte)1);
      out_stream.put((byte)0x00); // flags
      out_stream.put((byte)0x00); // flags
      
      // src.leastSignificantBitsParticipantId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.leastSignificantBitsParticipantId);
      // src.mostSignificantBitsParticipantId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.mostSignificantBitsParticipantId);
      // src.participantType
      out_stream.put((byte)src.participantType);
      // src.cpuUsage
      out_stream.put((byte)src.cpuUsage);
      // src.freeMemory
      while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
      out_stream.putInt(src.freeMemory);
      // src.numberOfConnectedVehicles
      while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
      out_stream.putInt(src.numberOfConnectedVehicles);
      // src.participantIp
      while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
      out_stream.putInt(src.participantIp.length()+1);
      out_stream.put(src.participantIp.getBytes());
      out_stream.put((byte)0);
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_fixed_size (  ) { return 0; }
  public int     marshall_key ( ByteBuffer out_stream,  LoadReportTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      size += 4; // for CDR 'header' 
      // src.leastSignificantBitsParticipantId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.mostSignificantBitsParticipantId
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
      
      // src.leastSignificantBitsParticipantId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.leastSignificantBitsParticipantId);
      // src.mostSignificantBitsParticipantId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.mostSignificantBitsParticipantId);
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_key_hash ( ByteBuffer out_stream,  LoadReportTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.leastSignificantBitsParticipantId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.mostSignificantBitsParticipantId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
    } else {
      out_stream.clear();
      out_stream.order(ByteOrder.BIG_ENDIAN);
      
      // src.leastSignificantBitsParticipantId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.leastSignificantBitsParticipantId);
      // src.mostSignificantBitsParticipantId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.mostSignificantBitsParticipantId);
      size = out_stream.position();
    }
    return size;
  }

  public boolean key_must_hash (  ) { return false; }

  // unmarshal variants
  public int     unmarshall ( LoadReportTopic t, ByteBuffer data, int s )    {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
      // t.leastSignificantBitsParticipantId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.leastSignificantBitsParticipantId = data.getLong();
      // t.mostSignificantBitsParticipantId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.mostSignificantBitsParticipantId = data.getLong();
      // t.participantType
      t.participantType = data.get();
      // t.cpuUsage
      t.cpuUsage = data.get();
      // t.freeMemory
      while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
      t.freeMemory = data.getInt();
      // t.numberOfConnectedVehicles
      while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
      t.numberOfConnectedVehicles = data.getInt();
      // t.participantIp
      {
        while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
        int    slen7;
        slen7   = data.getInt()-1;// skip trailing null
        byte[] sbytes7 = new byte[slen7];
        data.get(sbytes7,0,slen7);
        data.get(); // skip trailing null
        t.participantIp   = new String(sbytes7);
        sbytes7 = null;
      }
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key( LoadReportTopic t, ByteBuffer data, int s ) {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
      // t.leastSignificantBitsParticipantId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.leastSignificantBitsParticipantId = data.getLong();
      // t.mostSignificantBitsParticipantId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.mostSignificantBitsParticipantId = data.getLong();
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key_hash( LoadReportTopic t, ByteBuffer data, int s ) {
    data.order(ByteOrder.BIG_ENDIAN);
    // t.leastSignificantBitsParticipantId
    while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
    t.leastSignificantBitsParticipantId = data.getLong();
    // t.mostSignificantBitsParticipantId
    while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
    t.mostSignificantBitsParticipantId = data.getLong();
    return 0;
  }

  public int gen_typecode( ByteBuffer b ) {
    byte[] tc_data = { 
(byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x32, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'L', (byte)'o', (byte)'a', (byte)'d', (byte)'R', (byte)'e', (byte)'p', (byte)'o', (byte)'r', (byte)'t', (byte)'T', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)0x00, (byte)0x07, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x34, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x22, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'l', (byte)'e', (byte)'a', (byte)'s', (byte)'t', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'n', (byte)'t', (byte)'B', (byte)'i', (byte)'t', (byte)'s', (byte)'P', (byte)'a', (byte)'r', (byte)'t', (byte)'i', (byte)'c', (byte)'i', (byte)'p', (byte)'a', (byte)'n', (byte)'t', (byte)'I', (byte)'d', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x32, (byte)0x00, (byte)0x21, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'m', (byte)'o', (byte)'s', (byte)'t', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'n', (byte)'t', (byte)'B', (byte)'i', (byte)'t', (byte)'s', (byte)'P', (byte)'a', (byte)'r', (byte)'t', (byte)'i', (byte)'c', (byte)'i', (byte)'p', (byte)'a', (byte)'n', (byte)'t', (byte)'I', (byte)'d', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x22, (byte)0x00, (byte)0x10, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'p', (byte)'a', (byte)'r', (byte)'t', (byte)'i', (byte)'c', (byte)'i', (byte)'p', (byte)'a', (byte)'n', (byte)'t', (byte)'T', (byte)'y', (byte)'p', (byte)'e', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1a, (byte)0x00, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'c', (byte)'p', (byte)'u', (byte)'U', (byte)'s', (byte)'a', (byte)'g', (byte)'e', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1a, (byte)0x00, (byte)0x0b, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'f', (byte)'r', (byte)'e', (byte)'e', (byte)'M', (byte)'e', (byte)'m', (byte)'o', (byte)'r', (byte)'y', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2a, (byte)0x00, (byte)0x1a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'n', (byte)'u', (byte)'m', (byte)'b', (byte)'e', (byte)'r', (byte)'O', (byte)'f', (byte)'C', (byte)'o', (byte)'n', (byte)'n', (byte)'e', (byte)'c', (byte)'t', (byte)'e', (byte)'d', (byte)'V', (byte)'e', (byte)'h', (byte)'i', (byte)'c', (byte)'l', (byte)'e', (byte)'s', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x24, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'p', (byte)'a', (byte)'r', (byte)'t', (byte)'i', (byte)'c', (byte)'i', (byte)'p', (byte)'a', (byte)'n', (byte)'t', (byte)'I', (byte)'p', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,     };
    if (b != null) b.put(tc_data, 0, tc_data.length);
    return tc_data.length;
  }

  public int get_typecode_enc( ) {
    return (1 & 0x01);
  }

  // key field operations
  public boolean has_key (  ) { return true; }

  // <type> operations
  public LoadReportTopic         alloc ()              { return new LoadReportTopic(); }
  public void       clear (LoadReportTopic instance)   { instance.clear(); }
  public void       destroy (LoadReportTopic instance) { /* noop */ }
  public void       copy (LoadReportTopic to, LoadReportTopic from) { to.copy(from); }
  public boolean    get_field( String fieldname, CoreDX_FieldDef fdef ) { 
    return false;
  }
  private long cTypeSupport = 0;
}; // LoadReportTopic
