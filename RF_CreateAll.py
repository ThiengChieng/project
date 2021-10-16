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
from datetime import datetime
#import pydot

filepath = os.getcwd()+'\\javatoc'
print(filepath)
f = open("./javatoc", mode='r')
url = f.read()
ctojava = open("javatoc2", mode='w')
writeCtoJava = 0
#url = 'D:/notepad++/JAVA_program/new2/Hos_Data/Doc_Train/Feature/2019/'
lastloc = url.rfind('\\')  # 最後一個\的位置
last2loc = url.rfind('\\', 0, lastloc)+1
result = url[last2loc:lastloc]
print("result"+result)
allFileList = os.listdir(url)
for filepath in allFileList:
    if filepath.endswith('.csv'):
        fileurl=url+filepath
        #print("filepath"+filepath)
        print(fileurl)
        features = pd.read_csv(fileurl, encoding='mbcs')
        print(features)

        labels = np.array(features['診療分群'])  # label儲存目標變相(Medical time這欄)
        features = features.drop('診療分群', axis=1)  # 刪除medical time這欄
        feature_list = list(features.columns)  # 將標籤儲存至features_list

        features = pd.get_dummies(features) # 將文字特徵轉換為01矩陣
        print(features)
    # print(FT2.head(5))
        features = np.array(features) # 將features轉換為np數組 ※以科學符號方式保存
    # 創建隨機森林模型
    # 設定有100個決策樹，並設置隨機數種子碼=42
        rf = RandomForestClassifier(n_estimators=1500, random_state=45)
    # 開始訓練模型
        rf.fit(features, labels)


        lastloc = fileurl.rfind('\\')  # 最後一個\的位置
        #print(lastloc)
        last2loc = fileurl.rfind('\\', 0, lastloc)+1
        #print(last2loc)
        docnumber = fileurl[lastloc+1:lastloc+5]  # 儲存醫生卡號 不包含lastloc+5
        #print('docnumber'+docnumber)
        last3loc = fileurl.rfind('\\', 0, last2loc)
        #print(last3loc)
        year = fileurl[last3loc-4:last3loc]
        #print('year'+year)
        rloc = fileurl.rfind('Feature')
        #print(rloc)
        savepath = fileurl[0:rloc]
        #print('savepath'+savepath)
        
        savepath = savepath+"Model\\"+year+"\\"+result
        #print('savepath'+savepath)
        
        if not os.path.isdir(savepath):
            os.makedirs(savepath)
            
        writeCtoJava = savepath
        savepath = savepath+'\\'+docnumber+"_RF.pkl"
        print('savepath'+savepath)
        
        joblib.dump(rf, savepath)
print("writeCtoJava-----"+writeCtoJava)
ctojava.write(writeCtoJava+'\\')
ctojava.close()
    #savepath = savepath[0:savepath.rfind(year)] + year+"\\"+docnumber+"_RF.dot"

    #print(savepath) 
#tree = rf.estimators_[5]
#export_graphviz(tree, out_file=savepath,
                #feature_names=feature_list, filled=True, rounded=True, precision=1, special_characters=True)


#file_dot = open(savepath, mode='r', encoding='utf-8')
#dot_fontname = file_dot.read()
# print(dot_fontname)
#dot_fontname = dot_fontname.replace("fontname=helvetica", "fontname=sans")

#f3 = open(savepath[0:savepath.rfind(".")]+".txt", mode='w', encoding="mbcs")
#word = file_dot.read()
#f3.write(word)
# print(word)
#savepath = savepath[0:savepath.rfind(".")]+"_01.dot"
#export_graphviz(tree, out_file=savepath,
                #feature_names=feature_list, filled=True, rounded=True, max_depth=2, precision=1, special_characters=True)
#file_dot1 = open(savepath, mode='r', encoding='utf-8')
#dot_fontname1 = file_dot1.read()
# print(dot_fontname)
#dot_fontname1 = dot_fontname1.replace("fontname=helvetica", "fontname=sans")

# print(dot_fontname1)

#savepath = savepath[0:savepath.rfind("_01.dot")]+"_02.dot"
#file_dot2 = open(savepath, mode='w', encoding='utf-8')
#file_dot2.write(dot_fontname1)


#print(savepath)
#dotpath = savepath[savepath.rfind("\\")+1:]
#print(dotpath)
#pngpath = savepath[0:savepath.rfind("_02.dot")]+".png"
#print(pngpath)

#commond = "dot "+savepath+" -Tpng -o "+pngpath
#a = os.system(commond)
#print(a)
