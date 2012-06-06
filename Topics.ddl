#ifdef DDS_IDL
#define DDS_KEY __dds_key
#else
#define DDS_KEY
#endif

struct TruckTrackingInformationTopic
{
   double longitude;
   double latitude;
   float speed;
   long long time;
};

//Estrutura utilizada pelo GW para enviar os grupos para o dom�nio
struct GroupTopic
{
	long groupType;
	sequence<long> groupIDCollection;
};

struct TruckInformationTopic
{
   long long leastSignificantBitsGatewayId;
   long long mostSignificantBitsGatewayId;
   DDS_KEY long long leastSignificantBitsVehicleId;
   DDS_KEY long long mostSignificantBitsVehicleId;
   string tracker;
   sequence<TruckTrackingInformationTopic> information;
   //sequence<long> informationGroups;
   sequence<GroupTopic> groupCollection;
};

//Descontinuado. Utilizar GroupAdvertisementTopic
//struct TruckGroupInfoTopic
//{
	//long long leastSignificantBitsGatewayId;
	//long long mostSignificantBitsGatewayId;
	//DDS_KEY long long leastSignificantBitsVehicleId;
    //DDS_KEY long long mostSignificantBitsVehicleId;
	//sequence<long> informationGroups;
//};

struct PrivateMessageTopic
{
	DDS_KEY long long leastSignificantBitsVehicleId;
	DDS_KEY long long mostSignificantBitsVehicleId;
	long long leastSignificantBitsGatewayId;
	long long mostSignificantBitsGatewayId;
	long groupId;
	long groupType;
	sequence<octet> message;
	//boolean toOutsideDomain; //Pode usar este t�pico para enviar mensagens para os ve�culos e Dom�nio
};

//Utilizado como nested-structure no t�pico UnsentMessageTopic
struct MessageStructureTopic
{
	sequence<octet> message;
};

struct UnsentMessageTopic
{
	DDS_KEY long long leastSignificantBitsVehicleId;
	DDS_KEY long long mostSignificantBitsVehicleId;
	long long leastSignificantBitsGatewayId;
	long long mostSignificantBitsGatewayId;
	sequence<MessageStructureTopic> unsentMessages;
};

struct PingTopic
{
	DDS_KEY octet pingId;
	long long leastSignificantBitsGatewayId;
	long long mostSignificantBitsGatewayId;
	long long leastSignificantBitsVehicleId;
	long long mostSignificantBitsVehicleId;
	long long timestamp; //ou outro tipo de tempo. Necess�rio?
	boolean pingCore; //Informar se eh um pingCore
	boolean ping; //Assim � poss�vel usar o mesmo tempo para receber e enviar dados. Informa se � PING ou PONG
	long groupType;
	long groupId;
};

struct LoadReportTopic
{
	DDS_KEY long long leastSignificantBitsParticipantId;
	DDS_KEY long long mostSignificantBitsParticipantId;
	octet participantType; //GW, GroupDefiner, Monitor,... Podemos monitorar todos os elementos no dom�nio e saber seu tipo
	octet cpuUsage;
	long freeMemory;
	long numberOfConnectedVehicles;
	string participantIp; //�til no caso do GW onde o PoM-Manager precisa saber o IP de cada GW para gerar a lista
};

struct ConnectionReportTopic
{
	DDS_KEY long long leastSignificantBitsVehicleId;
	DDS_KEY long long mostSignificantBitsVehicleId;
	long long leastSignificantBitsGatewayId;
	long long mostSignificantBitsGatewayId;
	boolean connected;
};

//####################### In�cio T�picos utilizados pelo GroupDefiner V2

//Pode conter informa��es como nome, descri��o e outras coisas que n�o s�o necess�rias para funcionamento do SDDL
//struct GroupTopic
//{
//};

//T�pico utilizado pelo GroupDefiner v2 para informar as mudan�as de grupo
struct GroupAdvertisementTopic
{
	long long leastSignificantBitsGatewayId;
	long long mostSignificantBitsGatewayId;
	DDS_KEY long long leastSignificantBitsVehicleId;
    DDS_KEY long long mostSignificantBitsVehicleId;
	long groupType;
	sequence<long> groupOperationCollection;
};

//####################### Fim T�picos utilizados pelo GroupDefiner V2

//Criar t�pico. IDs V e GW, object e Tipo do mensagem
//struct SystemMessageTopic
//{
	//long long leastSignificantBitsGatewayId;
	//long long mostSignificantBitsGatewayId;
	//long long leastSignificantBitsVehicleId;
	//long long mostSignificantBitsVehicleId;
	//octet messageType; //Desconex�o de Ve�culo, Erro no sistema
	//sequence<octet> object; //Opcional
//};