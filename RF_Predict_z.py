import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.tree import export_graphviz
from sklearn.feature_selection import VarianceThreshold
import joblib
import os
#import pydot
import time
# 20210319
"""
已經將掛號順序做出還原
並且計算出預計到達時間
# 為了對應學長的部分 暫時先將第一筆資料處理掉 20210319
"""
filepath = os.getcwd()+'\\javatoc'
print(filepath)
f1 = open("./javatoc", mode='r')  # 接收檔案的路徑
f2 = open("./javatoc2", mode='r')  # 接收模型的路徑
dataurl = f1.read()
modurl = f2.read()
print("要預測的檔案的特徵路徑:" + dataurl)
print("選擇的模組路徑:"+modurl)


features = pd.read_csv(dataurl, encoding='mbcs')  # 要預測的檔案的特徵

lastloc = dataurl.rfind('\\')  # 最後一個\的位置
last2loc = dataurl.rfind('\\', 0, lastloc)+1
docnumber = dataurl[lastloc+1:lastloc+5]  # 儲存醫生卡號 不包含lastloc+5
year = dataurl[last2loc:lastloc]
# print(year)
# print(docnumber)
ordatapath = dataurl[0:dataurl.rfind("Feature")]+'Wa_Ch\\'+year+'\\'+docnumber+".csv"
print("原始資料檔:"+ordatapath)

rf = joblib.load(modurl)

FT = pd.read_csv(ordatapath, encoding="mbcs")


labels = np.array(features['診療分群'])  # label儲存目標變相(Medical time這欄)
features = features.drop('診療分群', axis=1)  # 刪除medical time這欄
# FTS = pd.get_dummies(features)  # 將文字特徵轉換為01矩陣


# 創建隨機森林模型
# 設定有100個決策樹，並設置隨機數種子碼=42

# PD = rf.predict(FTS)  # 進行預測
PD = rf.predict(features)
Alu = (PD-4)*2.11789+6.21657
MT2 = (PD-4)*2.11789+6.21657
MT3 = (PD-4)*2.11789+6.21657
# print(Alu)
# FT["診療分群"] = PD
# 新增一欄位medical time 並將PD這陣列當作此欄的值
FT["預估診療時間"] = Alu
# alu將預測結果運算出來

# FT.set_index(keys=['看診日期', '掛號序號'], inplace=True)  # 將結果運算完後將掛號順序重新排列
# FT.sort_index(inplace=True)
# FT.reset_index(inplace=True)

# MT2 = np.array(int)[MT]
# print(MT)
time_1 = np.array(FT['看診日期'])
time_2 = np.array(FT['班別早午診'])

MT = []
if time_2[0] == 'A':
    MT3[0] = 3600
else:
    MT3[0] = 21600
# MT3[0] = 3600
MT.append(time.strftime("%H:%M:%S", time.localtime(MT3[0])))
for i in range(1, MT2.size):
    if time_1[i-1] != time_1[i]:
        if time_2[i] == 'A':
            MT3[i] = 3600
        else:
            MT3[i] = 21600
    else:
        MT3[i] = MT3[i-1]+(MT2[i-1]*60)
    # struct_time = time.localtime(MT3[i])
    # timeString = time.strftime("%H:%M:%S", struct_time)
    # MT.append(timeString)
    MT.append(time.strftime("%H:%M:%S", time.localtime(MT3[i])))
# for i in range(1, MT2.size):  # 將時間數值儲存做排序
#   if time_1[i-1] != time_1[i]:
#       MT3[i] = 3600
#   else:
#       MT3[i] = MT3[i-1]+(MT2[i-1]*60)
#   struct_time = time.localtime(MT3[i])
#   MT.append(struct_time)
FT["建議到達時間"] = MT
# FT['arrive_time'] = pd.to_datetime(FT['arrive_time'])  # 將他再次轉為時間對象 才能進行排序
# FT['看診日期'] = pd.to_datetime(FT['看診日期'])  # 將他再次轉為時間對象 才能進行排序


savepath = dataurl[0:dataurl.rfind("Feature")]
savepath = savepath + "Mod_Re\\" + year

print("儲存檔案資料夾路徑:"+savepath)
if not os.path.isdir(savepath):
    os.makedirs(savepath)
savepath = savepath+"\\"+docnumber+"_RF.csv"
print("儲存檔案路徑:"+savepath)
# 將檔案儲存成csv ##20210306隨時注意 如果0059_result.csv檔案開著會導致錯誤
FT.to_csv(savepath, encoding="mbcs", index=False, sep=',')
# FT.to_csv("0059_result02.csv", index=False, sep=',')
ff=open("./ctojava",mode='w')
ff.write(savepath)
ff.close
