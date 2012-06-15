package DDSTopics;


// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.
import com.toc.coredx.DDS.*;
import java.nio.*;


public final class TruckInformationTopicTypeSupport implements TypeSupport{
  
  public ReturnCode_t register_type(DomainParticipant dp, String type_name) {
    if (dp.check_version( "3", "4", "0_p9" ) != 0) {
      System.out.println( "WARNING: TruckInformationTopic TypeSupport version does not match CoreDX Library version.");
      System.out.println( "This may cause software instability or crashes.");
    }
    return dp.register_type(this, type_name);
  }

  public String get_type_name()   { return "TruckInformationTopic"; }
  public long   getCTypeSupport() { return cTypeSupport; }

  public TruckInformationTopicTypeSupport() {
    TruckInformationTopic tmp = new TruckInformationTopic();
    cTypeSupport = DomainParticipant.createTypeSupport(this, 
                     getClass().getName(), tmp.getClass().getName());
  }

  // ------------------------------------------------------
  // implementation

  public DataReader   create_datareader(Subscriber sub, TopicDescription td, 
                                        SWIGTYPE_p__DataReader jni_dr) {
    return new TruckInformationTopicDataReader(sub, td, jni_dr);
  }
  public DataWriter   create_datawriter(Publisher  pub, Topic topic, SWIGTYPE_p__DataWriter jni_dw ) {
    return new TruckInformationTopicDataWriter(pub, topic, jni_dw);
  }

  // marshal variants
  public int     marshall ( ByteBuffer out_stream,  TruckInformationTopic src ) {
    int size = 0;
    if ( out_stream == null ) {
      size += 4; // for CDR 'header' 
      // src.leastSignificantBitsGatewayId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.mostSignificantBitsGatewayId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.leastSignificantBitsVehicleId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.mostSignificantBitsVehicleId
      size = (size+7) & 0xfffffff8;// align 8
      size += 8;
      // src.tracker
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      size += (src.tracker==null)?1:(src.tracker.length()+1);
      // src.information
      // src.information.length
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      if (src.information != null) {
        for (int i6 = 0; i6 < src.information.length; i6++ ) {
          // src.information[i6].longitude
          size = (size+7) & 0xfffffff8;// align 8
          size += 8;
          // src.information[i6].latitude
          size = (size+7) & 0xfffffff8;// align 8
          size += 8;
          // src.information[i6].speed
          size = (size+3) & 0xfffffffc;// align 4
          size += 4;
          // src.information[i6].time
          size = (size+7) & 0xfffffff8;// align 8
          size += 8;
        }
      }
      // src.groupCollection
      // src.groupCollection.length
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      if (src.groupCollection != null) {
        for (int i12 = 0; i12 < src.groupCollection.length; i12++ ) {
          // src.groupCollection[i12].groupType
          size = (size+3) & 0xfffffffc;// align 4
          size += 4;
          // src.groupCollection[i12].groupIDCollection
          // src.groupCollection[i12].groupIDCollection.length
          size = (size+3) & 0xfffffffc;// align 4
          size += 4;
          if (src.groupCollection[i12].groupIDCollection != null) {
            for (int i14 = 0; i14 < src.groupCollection[i12].groupIDCollection.length; i14++ ) {
              // src.groupCollection[i12].groupIDCollection[i14]
              size = (size+3) & 0xfffffffc;// align 4
              size += 4;
            }
          }
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
      
      // src.leastSignificantBitsGatewayId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.leastSignificantBitsGatewayId);
      // src.mostSignificantBitsGatewayId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.mostSignificantBitsGatewayId);
      // src.leastSignificantBitsVehicleId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.leastSignificantBitsVehicleId);
      // src.mostSignificantBitsVehicleId
      while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
      out_stream.putLong(src.mostSignificantBitsVehicleId);
      // src.tracker
      while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
      out_stream.putInt(src.tracker.length()+1);
      out_stream.put(src.tracker.getBytes());
      out_stream.put((byte)0);
      // src.information
      // src.information.length
      while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
      out_stream.putInt(src.information.length);
      for (int i6 = 0; i6 < src.information.length; i6++ ) {
          // src.information[i6].longitude
          while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
          out_stream.putDouble(src.information[i6].longitude);
          // src.information[i6].latitude
          while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
          out_stream.putDouble(src.information[i6].latitude);
          // src.information[i6].speed
          while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
          out_stream.putFloat(src.information[i6].speed);
          // src.information[i6].time
          while((out_stream.position() & 0x07) != 0) out_stream.put((byte)0x00); // align 8
          out_stream.putLong(src.information[i6].time);
      }
      // src.groupCollection
      // src.groupCollection.length
      while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
      out_stream.putInt(src.groupCollection.length);
      for (int i12 = 0; i12 < src.groupCollection.length; i12++ ) {
          // src.groupCollection[i12].groupType
          while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
          out_stream.putInt(src.groupCollection[i12].groupType);
          // src.groupCollection[i12].groupIDCollection
          // src.groupCollection[i12].groupIDCollection.length
          while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
          out_stream.putInt(src.groupCollection[i12].groupIDCollection.length);
          for (int i14 = 0; i14 < src.groupCollection[i12].groupIDCollection.length; i14++ ) {
              // src.groupCollection[i12].groupIDCollection[i14]
              while((out_stream.position() & 0x03) != 0) out_stream.put((byte)0x00); // align 4
              out_stream.putInt(src.groupCollection[i12].groupIDCollection[i14]);
          }
      }
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_fixed_size (  ) { return 0; }
  public int     marshall_key ( ByteBuffer out_stream,  TruckInformationTopic src ) {
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

  public int     marshall_key_hash ( ByteBuffer out_stream,  TruckInformationTopic src ) {
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
  public int     unmarshall ( TruckInformationTopic t, ByteBuffer data, int s )    {
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0x02)==0) { // CDR encoding
      // t.leastSignificantBitsGatewayId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.leastSignificantBitsGatewayId = data.getLong();
      // t.mostSignificantBitsGatewayId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.mostSignificantBitsGatewayId = data.getLong();
      // t.leastSignificantBitsVehicleId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.leastSignificantBitsVehicleId = data.getLong();
      // t.mostSignificantBitsVehicleId
      while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
      t.mostSignificantBitsVehicleId = data.getLong();
      // t.tracker
      {
        while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
        int    slen5;
        slen5   = data.getInt()-1;// skip trailing null
        byte[] sbytes5 = new byte[slen5];
        data.get(sbytes5,0,slen5);
        data.get(); // skip trailing null
        t.tracker   = new String(sbytes5);
        sbytes5 = null;
      }
      // t.information
      // t.information.length
      while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
      int    slen6 = data.getInt();
      t.information = new TruckTrackingInformationTopic[slen6];
      for (int i6 = 0; i6 < t.information.length; i6++ ) {
        t.information[i6] = new TruckTrackingInformationTopic();
        // t.information[i6].longitude
        while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
        t.information[i6].longitude = data.getDouble();
        // t.information[i6].latitude
        while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
        t.information[i6].latitude = data.getDouble();
        // t.information[i6].speed
        while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
        t.information[i6].speed = data.getFloat();
        // t.information[i6].time
        while((data.position() & 0x07) != 0) data.position(data.position()+1); // align 8
        t.information[i6].time = data.getLong();
      }
      // t.groupCollection
      // t.groupCollection.length
      while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
      int    slen12 = data.getInt();
      t.groupCollection = new GroupTopic[slen12];
      for (int i12 = 0; i12 < t.groupCollection.length; i12++ ) {
        t.groupCollection[i12] = new GroupTopic();
        // t.groupCollection[i12].groupType
        while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
        t.groupCollection[i12].groupType = data.getInt();
        // t.groupCollection[i12].groupIDCollection
        // t.groupCollection[i12].groupIDCollection.length
        while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
        int    slen14 = data.getInt();
        t.groupCollection[i12].groupIDCollection = new int[slen14];
        for (int i14 = 0; i14 < t.groupCollection[i12].groupIDCollection.length; i14++ ) {
          // t.groupCollection[i12].groupIDCollection[i14]
          while((data.position() & 0x03) != 0) data.position(data.position()+1); // align 4
          t.groupCollection[i12].groupIDCollection[i14] = data.getInt();
        }
      }
    }
    return 1; // 1==success, 0==failure
  }

  public int     unmarshall_key( TruckInformationTopic t, ByteBuffer data, int s ) {
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

  public int     unmarshall_key_hash( TruckInformationTopic t, ByteBuffer data, int s ) {
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
(byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x5c, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'T', (byte)'r', (byte)'u', (byte)'c', (byte)'k', (byte)'I', (byte)'n', (byte)'f', (byte)'o', (byte)'r', (byte)'m', (byte)'a', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)'T', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x07, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x30, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'l', (byte)'e', (byte)'a', (byte)'s', (byte)'t', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'n', (byte)'t', (byte)'B', (byte)'i', (byte)'t', (byte)'s', (byte)'G', (byte)'a', (byte)'t', (byte)'e', (byte)'w', (byte)'a', (byte)'y', (byte)'I', (byte)'d', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2e, (byte)0x00, (byte)0x1d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'m', (byte)'o', (byte)'s', (byte)'t', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'n', (byte)'t', (byte)'B', (byte)'i', (byte)'t', (byte)'s', (byte)'G', (byte)'a', (byte)'t', (byte)'e', (byte)'w', (byte)'a', (byte)'y', (byte)'I', (byte)'d', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2e, (byte)0x00, (byte)0x1e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'l', (byte)'e', (byte)'a', (byte)'s', (byte)'t', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'n', (byte)'t', (byte)'B', (byte)'i', (byte)'t', (byte)'s', (byte)'V', (byte)'e', (byte)'h', (byte)'i', (byte)'c', (byte)'l', (byte)'e', (byte)'I', (byte)'d', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2e, (byte)0x00, (byte)0x1d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'m', (byte)'o', (byte)'s', (byte)'t', (byte)'S', (byte)'i', (byte)'g', (byte)'n', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'n', (byte)'t', (byte)'B', (byte)'i', (byte)'t', (byte)'s', (byte)'V', (byte)'e', (byte)'h', (byte)'i', (byte)'c', (byte)'l', (byte)'e', (byte)'I', (byte)'d', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x20, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'t', (byte)'r', (byte)'a', (byte)'c', (byte)'k', (byte)'e', (byte)'r', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xc0, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'i', (byte)'n', (byte)'f', (byte)'o', (byte)'r', (byte)'m', (byte)'a', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xa0, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x94, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'T', (byte)'r', (byte)'u', (byte)'c', (byte)'k', (byte)'T', (byte)'r', (byte)'a', (byte)'c', (byte)'k', (byte)'i', (byte)'n', (byte)'g', (byte)'I', (byte)'n', (byte)'f', (byte)'o', (byte)'r', (byte)'m', (byte)'a', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)'T', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'l', (byte)'o', (byte)'n', (byte)'g', (byte)'i', (byte)'t', (byte)'u', (byte)'d', (byte)'e', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1a, (byte)0x00, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'l', (byte)'a', (byte)'t', (byte)'i', (byte)'t', (byte)'u', (byte)'d', (byte)'e', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'s', (byte)'p', (byte)'e', (byte)'e', (byte)'d', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x00, (byte)0x05, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'t', (byte)'i', (byte)'m', (byte)'e', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x92, (byte)0x00, (byte)0x10, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'g', (byte)'r', (byte)'o', (byte)'u', (byte)'p', (byte)'C', (byte)'o', (byte)'l', (byte)'l', (byte)'e', (byte)'c', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x70, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x64, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0b, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'G', (byte)'r', (byte)'o', (byte)'u', (byte)'p', (byte)'T', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)0x00, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x1c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'g', (byte)'r', (byte)'o', (byte)'u', (byte)'p', (byte)'T', (byte)'y', (byte)'p', (byte)'e', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2e, (byte)0x00, (byte)0x12, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'g', (byte)'r', (byte)'o', (byte)'u', (byte)'p', (byte)'I', (byte)'D', (byte)'C', (byte)'o', (byte)'l', (byte)'l', (byte)'e', (byte)'c', (byte)'t', (byte)'i', (byte)'o', (byte)'n', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,     };
    if (b != null) b.put(tc_data, 0, tc_data.length);
    return tc_data.length;
  }

  public int get_typecode_enc( ) {
    return (1 & 0x01);
  }

  // key field operations
  public boolean has_key (  ) { return true; }

  // <type> operations
  public TruckInformationTopic         alloc ()              { return new TruckInformationTopic(); }
  public void       clear (TruckInformationTopic instance)   { instance.clear(); }
  public void       destroy (TruckInformationTopic instance) { /* noop */ }
  public void       copy (TruckInformationTopic to, TruckInformationTopic from) { to.copy(from); }
  public boolean    get_field( String fieldname, CoreDX_FieldDef fdef ) { 
    return false;
  }
  private long cTypeSupport = 0;
}; // TruckInformationTopic
