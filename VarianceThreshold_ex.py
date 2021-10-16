from sklearn.feature_selection import VarianceThreshold
import pandas as pd
import numpy as np
import sys
import importlib
import os
# importlib.reload(sys)
# url = "https://raw.githubusercontent.com/jbrownlee/Datasets/master/pima-indians-diabetes.data.csv"
filepath = os.getcwd()+'\\javatoc'
print(filepath)
f = open(filepath, mode='r')
#url = 'F:\\googledrive\\Topic\\Python\\sklearn\\0059_V01_ALL.csv'
url = f.read()
print(url)
# names = ['preg', 'plas', 'pres', 'skin',
#        'test', 'mass', 'pedi', 'age', 'class']

# dataframe = pd.read_csv(url, names=names)
#dataframe = pd.read_csv(url, engine='python')
dataframe = pd.read_csv(url, encoding='mbcs')  # 解碼方式改為ansi解碼
# print(dataframe)
# FT = pd.read_csv(
#    'F:\\googledrive\\Topic\\Python\\sklearn\\8個欄位範本.csv', engine='python')  # 八個欄位的範本
# dataframe = dataframe.drop(['看診日期', '看診科別', '看診診間', '病歷號', '醫師卡號', '跟診護理師卡號', '病患出生日期', '掛號類別', '掛號序號', '班別早午診', '診斷碼1', '診斷碼2', '診斷碼3', '診斷碼4', '診斷碼5', '診斷碼6', '診斷碼7', '診斷碼8', '診斷碼9',
#                            '診斷碼10', '自助報到插卡時間', '醫師插卡時間', '醫師批價時間1', '醫師批價時間2', '醫師批價時間3', '醫師批價時間4', '醫師批價時間5', '醫師批價時間6', '醫師批價時間7', '醫師批價時間8', '醫師批價時間9', '預估看診時間', '診療時間', '診療分群'], axis=1)
dataframe = dataframe.drop(['看診日期', '看診科別', '看診診間', '病歷號', '醫師卡號', '跟診護理師卡號', '病患性別', '病患出生日期', '初複診', '掛號類別', '掛號序號', '班別早午診', '前次是否開立醫囑', '診斷碼1', '診斷碼2', '診斷碼3', '診斷碼4', '診斷碼5', '診斷碼6', '診斷碼7', '診斷碼8', '診斷碼9',
                            '診斷碼10', '自助報到插卡時間', '醫師插卡時間', '醫師批價時間1', '醫師批價時間2', '醫師批價時間3', '醫師批價時間4', '醫師批價時間5', '醫師批價時間6', '醫師批價時間7', '醫師批價時間8', '醫師批價時間9', '預估看診時間', '診療時間', '診療分群'], axis=1)

VT = VarianceThreshold(0.0)
features1 = VT.fit_transform(dataframe)  # 方差過濾
features3 = VT.get_support()  # 確認誰true誰false
# new = pd.DataFrame({label[0]: features3[0], label[1]: features3[1], label[2]: features3[2], label[3]: features3[3],
#                   label[4]: features3[4], label[5]: features3[5], label[6]: features3[6], label[7]: features3[7]})
#new = pd.DataFrame(features3)
#label = label.append(new)
# print(features3[1])
label = []
for i in range(7):
    # print(features3[i])
    if features3[i]:
        label.append(i)
print(label)
# np.set_printoptions(linewidth=400)
#np.savetxt("0059_FT.txt", label)
lastloc = url.rfind('\\')  # 最後一個\的位置
last2loc = url.rfind('\\', 0, lastloc)+1
docnumber = url[lastloc+1:lastloc+5]  # 儲存醫生卡號 不包含lastloc+5
year = url[last2loc:lastloc]
rloc = url.rfind('Wa_Ch')
savepath = url[0:rloc]
# print(url)
# print(url[0:rloc])
# print(savepath)
# print(url[last2loc:lastloc])
# print(year)
# print(url[lastloc+1:lastloc+5])
# print(docnumber)
savepath = savepath+"Feature\\"+year

#path = 'F:\\googledrive\\模擬專題資料夾環境\\Hos_Data\\Doc_Train\\Feature\\2019'
# os.makedirs(savepath)

if not os.path.isdir(savepath):
    os.makedirs(savepath)

savepath = savepath+"\\"+docnumber+"_FT.txt"

np.savetxt(savepath, label)
# print(features3[5])
# print(label)
# data = pd.DataFrame(label[0]: features3[0], label[1]: features3[1], label[2]: features3[2], label[3]: features3[3], label[4]: features3[4], label[5]: features3[5], label[6]: features3[6], label[7]: features3[7])
#data = pd.DataFrame(columns=label)
#np.savetxt("0059_FT.csv", features3)

#features3.to_csv("0059_FT.csv", index=False, sep=',')
# print(dataframe)
