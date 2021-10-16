from sklearn.feature_selection import chi2
from sklearn.feature_selection import SelectKBest
import pandas as pd
import numpy as np
import os
""" test
url = "https://raw.githubusercontent.com/jbrownlee/Datasets/master/pima-indians-diabetes.data.csv"
names = ['preg', 'plas', 'pres', 'skin',
         'test', 'mass', 'pedi', 'age', 'class']

dataframe = pd.read_csv(url, names=names)
print(dataframe)
array = dataframe.values
X = array[:, 0:8]
Y = array[:, 8]

# Feature extraction
test = SelectKBest(score_func=chi2, k=4)
fit = test.fit(X, Y)

# Summarize scores
np.set_printoptions(precision=3)
print(fit.scores_)
"""
filepath = os.getcwd()+'\\javatoc'
print(filepath)
f = open("./javatoc", mode='r')
url = f.read()
dataframe = pd.read_csv(url, delimiter=',', encoding='mbcs')  # 解碼方式改為ansi解碼
dataframe = dataframe.drop(['看診日期', '看診科別', '看診診間', '病歷號', '醫師卡號', '跟診護理師卡號', '病患性別', '病患出生日期', '掛號類別', '初複診', '掛號序號', '班別早午診', '前次是否開立醫囑', '診斷碼1', '診斷碼2', '診斷碼3', '診斷碼4', '診斷碼5', '診斷碼6', '診斷碼7', '診斷碼8', '診斷碼9',
                            '診斷碼10', '自助報到插卡時間', '醫師插卡時間', '醫師批價時間1', '醫師批價時間2', '醫師批價時間3', '醫師批價時間4', '醫師批價時間5', '醫師批價時間6', '醫師批價時間7', '醫師批價時間8', '醫師批價時間9', '預估看診時間', '診療時間'], axis=1)
array = dataframe.values
X = array[:, 1:9]
y = array[:, 0]
# print(X)
# print(y)
test = SelectKBest(score_func=chi2, k=4)
fit = test.fit(X, y)
features3 = test.get_support()
# print(fit)
# print(features3)
label = []
for i in range(8):
    # print(features3[i])
    if features3[i]:
        label.append(i)
print(label)

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


if not os.path.isdir(savepath):
    os.makedirs(savepath)

savepath = savepath+"\\"+docnumber+"_CHi2.txt"

np.savetxt(savepath, label)

#system("pause")
