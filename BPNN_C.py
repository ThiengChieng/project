from tensorflow.keras import Sequential
from tensorflow.keras.layers import Dense
import os
import numpy as np  
import pandas as pd
###########################################################################################################################################################
#find open address and get file name
f = open('javatoc')
Open_Addres = f.readline()
#second_line = f.readline()
f.close()
str_cut1='.'
str_cut2="\\"
File_Name = Open_Addres[Open_Addres.index(str_cut2,30)+1:Open_Addres.index(str_cut1,32)]
File_Year = Open_Addres[Open_Addres.index(str_cut2,20)+1:Open_Addres.index(str_cut2,30)]
print('name:'+File_Name)
print('year:'+File_Year)
###########################################################################################################################################################
path = 'Hos_Data/Doc_Train/Model'+'/'+File_Year
#path = File_Name
if not os.path.isdir('Hos_Data/Doc_Train/Model'):
    os.mkdir('Hos_Data/Doc_Train/Model')
if not os.path.isdir(path):
    os.mkdir(path)
###########################################################################################################################################################
#Open_Addres='PY/BPNN/0059/00591.csv'
Save_Model_Addres=path+'/'+File_Name+'_BPNN.h5'
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
print("ck[1]=")
print(ck[1])
print('requ=')
print(requ)
#-------------------OverWrite csv(START)---------------------
'''
print("---------Start Write BPNN_FComuni.....-----------")
dataframe = pd.DataFrame(ck)
dataframe.to_csv(path+'/'+File_Name+"_BPNN_FComni.csv",index=False,header=None,sep=',',encoding='big5')
print("---------Write BPNN_FComuni Finish!--------------")
#-------------------OverWrite csv(FIN)-----------------------
'''
##################################################################FILE1_FOR_TEST###########################################################################

DataNb = RdTemp.shape[0]
print(DataNb) #==total data number 
####vvvvvvvvvvvvv  for 0,1 array  vvvvvvvvvvvvvvvvv
dignosis_class=np.zeros((DataNb, 25))       #dignosis class just have 25 kinds & initial
gender_class=np.zeros((DataNb,2))           #boy(1,0)<===>girl(0,1)                        "(())" MUST NOT BE "()" or it will have error
TranOut=np.zeros((DataNb,10))
#requ=ColNb-1#

TranSet=[[0]*(requ-lisan_array) for i in range(DataNb)]
#TranOut=[0]*DataNb
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
        elif j==ColNb-1:
            TranOut[i][int(RdTemp[i][j])-1] = 1
        else:
            if(RdTemp[i][j]=="Y"):
                TranSet[i][k] = 1
            elif(RdTemp[i][j]=="N"):
                TranSet[i][k] = 0
            else:
                TranSet[i][k] = float(RdTemp[i][j])
          #  print(RdTemp[i][j])
            k=k+1
for j in range(ColNb):
    if che[j]=="病患性別_轉換":      
        requ=requ+1
        TranSet=np.hstack((TranSet,gender_class))#gender_class append. into TranSet
    elif che[j]=="掛號類別_轉換": 
        requ=requ+24
        TranSet=np.hstack((TranSet,dignosis_class))#dignosis_class append. into TranSet


print (TranSet[0])
print (TranOut[0])
print(requ)
##############################################################################################################
TranSet=np.array(TranSet)
print("TranSet.Col=")
print(TranSet.shape[1])
print(type(TranOut))


# 建立一個 squential 的 model
model = Sequential()
# 建立一個輸入及輸出都是一維(輸入 X 輸出 Y)的全連接型態的神經層
dense = Dense(units=(requ),input_dim=requ)  #units-->second_NN input_dim-->first_NN
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
weights = model.layers[0].get_weights()[0] 
biases = model.layers[0].get_weights()[1] 
print(weights) 
'''

print("requ=",requ)
print("TranSet=")
print(TranSet)
history=model.fit(TranSet,TranOut,epochs=80,verbose=1)   #############epochs is dieadia
weights_0 = model.layers[0].get_weights()[0] 
print(weights_0)
weights_1 = model.layers[1].get_weights()[0]
##write txt for show model
#writeT = model.summary()
writeT = weights_0
f = open(path+'/'+File_Name+'_BPNN'+'.txt','w')
print("---------------------BPNN Layers[0].weight----------------------", file = f)
print(writeT, file = f)
print("---------------------BPNN Layers[1].weight----------------------", file = f)
writeT = weights_1
print(writeT, file = f)
f.close()

f = open('ctojava','w')
f.write(path+'/'+File_Name+'_BPNN'+'.txt')
f.close()
##write txt for show model done 

model.save(Save_Model_Addres)
print("save model done!")
print("___________________BPNN ALL done!___________________")