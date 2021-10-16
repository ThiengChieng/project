from tensorflow.keras import Sequential
from tensorflow.keras.models import load_model
from tensorflow.keras.layers import Dense
import datetime 
import os
import numpy as np  
import pandas as pd
###########################################################################################################################################################
Output_Result_Address='Hos_Data/Doc_Test/Mod_Re/'
Cluster_Path='Hos_Data/Doc_Train/Wa_Ch/'
Date_Path='Hos_Data/Doc_Test/Wa_Ch/'
print('loading model......')
#find open address and get file name
f = open('javatoc')
Open_Addres = f.readline() #Hos_Data\Doc_Test\Feature\20XX\XXXX.csv
#second_line = f.readline()
print('Open_Addres='+Open_Addres)
f.close()
f = open('javatoc2')
Model_Address = f.readline() #Hos_Data\Doc_Tran\Model\20XX\XXXX_BPNN.h5
#second_line = f.readline()
print('Model_Address='+Model_Address)
f.close()

str_cut1='\\'
str_cut2='.'
File_Name = Open_Addres[Open_Addres.index(str_cut1,len(Open_Addres)-12)+1:Open_Addres.index(str_cut2,30)]
print('File_Name='+File_Name)
File_Year = Open_Addres[Open_Addres.index(str_cut1,len(Open_Addres)-16)+1:Open_Addres.index(str_cut1,len(Open_Addres)-12)]
print('File_Year='+File_Year)
File_Year_For_Cluster = Model_Address[Model_Address.index(str_cut1,len(Model_Address)-20)+1:Model_Address.index(str_cut1,len(Model_Address)-16)]

print('File_Year_For_Cluster: '+File_Year_For_Cluster)

Date_File_Path      =  Date_Path    +File_Year+'/'+File_Name+'.csv'
Cluster_File_Path   =  Cluster_Path +File_Year_For_Cluster+'/'+File_Name+'_Cluster.txt'
Output_Result_Address=Output_Result_Address+File_Year+'/'
path = 'Hos_Data/Doc_Train/Model'+'/'+File_Year #path : Model+Year
#path = File_Name
print('Model_Address:',Model_Address)
model = load_model(Model_Address)
print('vvvvvv print model summary vvvvvv')
print(model.summary())
print('-------------------------------------------------------------')
##################################################################FILE1_FOR_TEST###########################################################################
ck=[["病患性別_轉換","初複診_轉換","前次是否開立醫囑_轉換","看診星期","病患年齡","班別早午診_轉換","實際看診順序","掛號類別_轉換","診療分群"],[0,0,0,0,0,0,0,0,0]]
##################################################################FILE1_FOR_TEST###########################################################################
df1 = pd.read_csv(Open_Addres,header=None,encoding='big5')
print(df1)
ColNb= df1.shape[1]
RdTemp=df1.drop([0,0])
RdTemp=np.array(RdTemp)
che=np.array(df1.iloc[0])
requ=0
lisan_array=0
for i in range(ColNb):
    for j in range(9):  #9 classes could insert into BPNN (0~8)
        if che[i] == ck[0][j]:
            ck[1][j]=1
            requ=requ+1
            if j==0 or j==7:
                lisan_array=lisan_array+1
#print(che)#
requ=requ-1 #the last is output
print("ck[1]= " ,ck[1])
print('requ: ',requ)
###########################################################################################################################################################
df1 = pd.read_csv(Open_Addres,header=None,encoding='big5') #in this address is for test_set location
print(df1)
ColNb= df1.shape[1]
RdTemp=df1.drop([0,0])
RdTemp=np.array(RdTemp)
che=np.array(df1.iloc[0])
DataNb = RdTemp.shape[0]
print('DataNb:' ,DataNb) #==total data number 
####vvvvvvvvvvvvv  for 0,1 array  vvvvvvvvvvvvvvvvv
dignosis_class=np.zeros((DataNb, 25))       #dignosis class just have 25 kinds & initial
gender_class=np.zeros((DataNb,2))           #boy(1,0)<===>girl(0,1)                        "(())" MUST NOT BE "()" or it will have error
#TestOut=np.zeros((DataNb,10))
print("Initial dignosis & gender 0,1 array done\n")
requ=ColNb-1#
print('lisan_array: ',lisan_array)
TestSet=[[0]*(requ-lisan_array) for i in range(DataNb)]
#TestOut=[0]*DataNb
for i in range(DataNb):
    k=0
    for j in range(ColNb-1):
        if che[j]=="病患性別_轉換":
            if(RdTemp[i][j]=="M"):      #boy(0,1)  girl(1,0)
                gender_class[i][1]=1
            elif (RdTemp[i][j]=="F"):
                gender_class[i][0]=1
            else:
                gender_class[i][int(RdTemp[i][j])]=1
        elif che[j]=="掛號類別_轉換":
            dignosis_class[i][(int(RdTemp[i][j])-1)] = 1
        else:
            if(RdTemp[i][j]=="Y"):
                TestSet[i][k] = 1
            elif(RdTemp[i][j]=="N"):
                TestSet[i][k] = 0
            else:
                TestSet[i][k] = float(RdTemp[i][j])
          #  print(RdTemp[i][j])
            k=k+1
for j in range(ColNb):
    if che[j]=="病患性別_轉換":      
        requ=requ+1
        TestSet=np.hstack((TestSet,gender_class))#gender_class append. into TestSet
    elif che[j]=="掛號類別_轉換": 
        requ=requ+24
        TestSet=np.hstack((TestSet,dignosis_class))#dignosis_class append. into TestSet


print (TestSet[0])


##################################################################Read TestSet.CSV#########################################################################
###########################################################################################################################################################
print('===========================================')
print('---------fitting......----------')
Y_pre=model.predict(TestSet,verbose=0)
print(type(Y_pre))
#!!!WARNNING!!!===>Test_Out is undefine
##------------------------------find max() in OutPut------------
Test_Out=np.zeros(DataNb)
for i in range(DataNb):
    for j in range(10):
        if max(Y_pre[i])==Y_pre[i][j]:
            Test_Out[i]=j+1
#Y_pre=np.round(Y_pre)
print("--------fitting done!----------")
print(Test_Out)##BPNN guess in cluster 1~10
#################################################################Test_Set put into BPNN###################################################################
##########################################################################################################################################################
#Cluster_Path='Hos_Data/Doc_Train/Wa_Ch/'
f = open(Cluster_File_Path)
Cluster=np.zeros(11)
for i in range(9):
    Temp = f.readline()
    Cluster[i+1] =float(Temp[:Temp.index('\n',1)])
Cluster[10]  = float(f.readline())##!!cluster.txt only have 9 '\n'
f.close()
    #File_Name = Open_Addres[Open_Addres.index(str_cut1,28)+1:Open_Addres.index(str_cut2,30)]

for i in range(DataNb):
    for j in range(10):
        if (j+1) ==Test_Out[i]:
            Test_Out[i] = Cluster[j+1]
print('****************************************\n print Test_Out in Cluster (healing time)\n**************************************** ')
print(Test_Out)
################################################find cluster and transform "Test_Out" by Cluster##########################################################
##########################################################################################################################################################
in_date = '09:00:00'
dt = datetime.datetime.strptime(in_date, "%H:%M:%S")
df2 = pd.read_csv(Date_File_Path,header=None,encoding='big5') #in this address is for find date location
RdTemp1=df2.drop([0,0])
print('DataNb=',DataNb)
Date_by_Test = np.empty(DataNb,dtype='object')
Predict_Time = np.empty(DataNb,dtype='object')
for i in range(DataNb):     
    #print('i=',i)
    #print(RdTemp1[0][i+1])
    Date_by_Test[i]=RdTemp1[0][i+1] #load Wa_Ch Date into Date_by_Test array
for i in range(DataNb):
    if(i==0):
        Predict_Time[i] = str(dt)[str(dt).index(' ',1)+1:]
    else:
        if(Date_by_Test[i]!=Date_by_Test[i-1]):
            dt = datetime.datetime.strptime(in_date, "%H:%M:%S")
            Predict_Time[i] = str(dt)[str(dt).index(' ',1)+1:]
        else:
            dt = dt + datetime.timedelta(minutes=Test_Out[i-1])
            Predict_Time[i] = str(dt)[str(dt).index(' ',1)+1:]
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~輸出預測建議到達時間~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
print('\n\n\n............................Output result to '+Output_Result_Address+File_Name+'............................')
#Output_Result_Address='Hos_Data/Doc_Test/Mod_Re/'
if not os.path.isdir('Hos_Data/Doc_Test/Mod_Re/'):
    os.mkdir('Hos_Data/Doc_Test/Mod_Re/')
if not os.path.isdir(Output_Result_Address):
    os.mkdir(Output_Result_Address)
Temp = np.ndarray(shape=(DataNb+1,2),dtype='object')
for i in range(DataNb):
    Temp[i+1][0]=Test_Out[i]
    Temp[i+1][1]=Predict_Time[i]
Temp[0][0]='預估診療時間'
Temp[0][1]="建議到達時間"
df2 = pd.read_csv(Date_File_Path,header=None,encoding='big5')
print(df2)
df2=np.hstack((df2,Temp))
print('\n\n\n')
print(df2)
print('\n\n\n')

Output =pd.DataFrame(df2)#,columns=["看診日期","看診科別","看診診間","病歷號","醫師卡號","跟診護理師卡號","病患性別","病患出生日期","掛號類別","初複診","掛號序號","班別早午診","前次是否開立醫囑","診斷碼1","診斷碼2","診斷碼3","診斷碼4","診斷碼5","診斷碼6","診斷碼7","診斷碼8","診斷碼9","診斷碼10","自助報到插卡時間","醫師插卡時間","醫師批價時間1","醫師批價時間2","醫師批價時間3","醫師批價時間4","醫師批價時間5","醫師批價時間6","醫師批價時間7","醫師批價時間8","醫師批價時間9","預估看診時間","診療時間","診療分群","看診星期","病患年齡","班別早午診_轉換","實際看診順序","掛號類別_轉換","病患性別_轉換","前次是否開立醫囑_轉換","初複診_轉換","預估診療時間","建議到達時間"]
Output.to_csv(Output_Result_Address+'/'+File_Name+"_BPNN.csv",index=False,header=None,sep=',',encoding='big5')


f = open('ctojava','w')
f.write(Output_Result_Address+File_Name+"_BPNN.csv")
f.close()
'''
df1 = pd.read_csv(r'0059/0059_V01.csv',encoding='big5')
#print(df1)
RdTemp = df1[["看診日期","看診科別","看診診間","病歷號","醫師卡號","跟診護理師卡號","病患性別","病患出生日期","掛號類別","初複診","掛號序號","班別早午診","前次是否開立醫囑","診斷碼1","診斷碼2","診斷碼3","診斷碼4","診斷碼5","診斷碼6","診斷碼7","診斷碼8","診斷碼9","診斷碼10","自助報到插卡時間","醫師插卡時間","醫師批價時間1","醫師批價時間2","醫師批價時間3","醫師批價時間4","醫師批價時間5","醫師批價時間6","醫師批價時間7","醫師批價時間8","醫師批價時間9","預估看診時間","診療時間","診療分群","看診星期","病患年齡","班別早午診_轉換","實際看診順序","掛號類別_轉換"]]
#               0           1           2           3       4           5               6           7              ?8       9       10          11              12               13         14      15      16          17          18      19          20      21      22              23                 24           25              26                  27          28              29              30              31              32                  33              34          35        36        37          38          39                  40          41
RdTemp = np.array(RdTemp)
print(RdTemp[0][36])

DataNb = df1.shape[0]
#print(DataNb) #==total data number 
####vvvvvvvvvvvvv  for 0,1 array  vvvvvvvvvvvvvvvvv####
dignosis_class=np.zeros((DataNb, 24))       #dignosis class just have 24 kinds & initial
gender_class=np.zeros((DataNb,2))           #boy(1,0)<===>girl(0,1)                        "(())" MUST NOT BE "()" or it will have error
TranOut=np.zeros((DataNb,10))

ColNb =  df1.shape[1]
#print(ColNb)  #--total Col  number =14 #2,3,4,5,7,9,10,11,12,13,36,37,38,39
#-------------------ReWrite csv(START)-----------------------
requ=6#????6,9,12,37,38,39,40,41
#41 transform in to "dignosis_class" array
TranSet=[[0]*requ for i in range(DataNb)]
#TranOut=[0]*DataNb
for i in range(DataNb):
    k=0
    for j in range(ColNb):
        if(j==41):
            dignosis_class[i][RdTemp[i][j]] = 1
        if j==9 or j==12 or j==37 or j==38 or j==39 or j==40 :#6,9,12,37,38,39,40,41
            
            if(str(RdTemp[i][j]).isalpha()==True):
                #TranSet[i][k] = float(ord(RdTemp[i][j]))
                if(RdTemp[i][j]=="M" or RdTemp[i][j]=="Y"):
                    TranSet[i][k] = 1
                else:
                    TranSet[i][k] = 0
            elif (str(RdTemp[i][j]).isdigit()==True):
                TranSet[i][k] = float(RdTemp[i][j])
            else:
                TranSet[i][k] = float(RdTemp[i][j])  
          #  print(RdTemp[i][j])
            k=k+1
        elif j==36:
            TranOut[i][RdTemp[i][j]-1] = 1
        elif j==6:
            if(RdTemp[i][j]=="M"):
                gender_class[i][1]=1
            elif (RdTemp[i][j]=="F"):
                gender_class[i][0]=1
            else:
                gender_class[i][RdTemp[i][j]]=1
TranSet=np.hstack((TranSet,dignosis_class))#dignosis_class append. into TranSet
TranSet=np.hstack((TranSet,gender_class))#gender_class append. into TranSet

print (TranSet[0])
print (TranOut[0])
##############################################################################################################

print(type(TranOut))


# 建立一個 squential 的 model
model = Sequential()
# 建立一個輸入及輸出都是一維(輸入 X 輸出 Y)的全連接型態的神經層
dense = Dense(units=(requ+24+2),input_dim=requ+24+2)  #units-->second_NN input_dim-->first_NN
# 將神經層加到 model 裡
model.add(dense)
# compile 是用來安排學習過程的，optimizer 可以輸入一個 optimizer instance 或直接輸入該 optimizer class 的名字的字串。loss 也是一樣的用法。
# compile() 其實還有第三個參數 metrics, 那是用在「分類」的問題上。
model.add(Dense(units=10))
# compile 文件: https://keras.io/getting-started/sequential-model-guide/#compilation
# https://keras.io/optimizers/
# https://keras.io/losses/
model.compile(loss='mean_squared_error', optimizer='sgd')
print(model.summary())
'''
'''
weights = model.layers[0].get_weights()[0] 
biases = model.layers[0].get_weights()[1] 
print(weights) 
'''
'''


history=model.fit(TranSet,TranOut,epochs=8,verbose=1)   #############epochs is dieadia

weights = model.layers[0].get_weights()[0] 
print(weights)

model.save("BPNN_S02.h5")
print("save model done!")
'''