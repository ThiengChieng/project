import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.tree import export_graphviz
from sklearn.feature_selection import VarianceThreshold
from sklearn import tree
import joblib
import os
from sklearn import datasets
import re
#import pydot

filepath = os.getcwd()+'\\javatoc'
print(filepath)
f = open("./javatoc", mode='r')
url = f.read()
features = pd.read_csv(url, encoding='mbcs')
"""
labeltargettw = pd.read_csv(
    "D:\\datafile\\googledrive\\Topic\\Python\\sklearn\\8個欄位範本.csv", encoding='mbcs')
labeltargeten = pd.read_csv(
    "D:\\datafile\\googledrive\\Topic\\Python\\sklearn\\8ENGLISH.csv", encoding='mbcs')
label11 = ["看診星期", "病患年齡", "班別早午診_轉換", "實際看診順序",
           "掛號類別_轉換", "病患性別_轉換", "前次是否開立醫囑_轉換", "初複診_轉換"]
labeltargettw = list(labeltargettw.columns)
labeltargeten = list(labeltargeten.columns)
"""
labels = np.array(features['診療分群'])  # label儲存目標變相(Medical time這欄)
features = features.drop('診療分群', axis=1)  # 刪除medical time這欄
feature_list = list(features.columns)  # 將標籤儲存至features_list
print(features)

#features = pd.get_dummies(features)  # 將文字特徵轉換為01矩陣
print(feature_list)
# print(FT2.head(5))
features = np.array(features)  # 將features轉換為np數組 ※以科學符號方式保存
# 創建隨機森林模型
# 設定有100個決策樹，並設置隨機數種子碼=42
rf = RandomForestClassifier(n_estimators=1500, random_state=42)
# 開始訓練模型
rf.fit(features, labels)
"""
label = []
for i in range(len(feature_list)):
    for j in range(len(labeltargettw)):
        if labeltargettw[j] == feature_list[i]:
            label.append(labeltargeten[j])

print(label)
"""

lastloc = url.rfind('\\')  # 最後一個\的位置
last2loc = url.rfind('\\', 0, lastloc)+1
docnumber = url[lastloc+1:lastloc+5]  # 儲存醫生卡號 不包含lastloc+5
year = url[last2loc:lastloc]
rloc = url.rfind('Feature')
savepath = url[0:rloc]

savepath = savepath+"Model\\"+year

if not os.path.isdir(savepath):
    os.makedirs(savepath)

savepath = savepath+"\\"+docnumber+"_RF.pkl"
print(savepath)

joblib.dump(rf, savepath)

savepath = savepath[0:savepath.rfind(year)] + year+"\\"+docnumber+"_RF.dot"

print(savepath)
tree = rf.estimators_[5]
export_graphviz(tree, out_file=savepath,
                feature_names=feature_list, rounded=True, precision=1, special_characters=True)
f2 = open(savepath, mode='r', encoding='utf-8')
f4 = open("./ctojava",mode='w',encoding='mbcs')
savepath=savepath[0:savepath.rfind(".")]+".txt"
f3 = open(savepath, mode='w', encoding="mbcs")
f4.write(savepath)
word = f2.read()
f3.write(word)
f.close()
f2.close()
f3.close()
f4.close()
#print(word)
