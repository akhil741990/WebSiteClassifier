import pandas as pd
import numpy as np
import pickle
from sklearn.feature_extraction.text import TfidfVectorizer
from keras.models import Sequential
from keras.layers import Activation, Dense, Dropout
from sklearn.preprocessing import LabelBinarizer
import sklearn.datasets as skds
from pathlib import Path


trainPath = "../data/train";


files_train = skds.load_files(trainPath,load_content=False);


file_index = files_train.target;
file_labels  = files_train.target_names;
file_names   = files_train.filenames;

# print("index:",file_index);
# print("file_lables:",file_labels);
# print("file_names:", file_names);


data_tags = ["filename","category","data"]
data_list = []
i = 0;
for f in file_names :
    try:
        data_list.append((f,file_labels[file_index[i]],Path(f).read_text()))
        i = i +1
    except:
        pass    
    
#print("data_list : ", data_list);

data = pd.DataFrame.from_records(data_list, columns=data_tags)    


train_size = int(len(data) * 0.8);

train_data = data["data"][:train_size];
train_category = data["category"][:train_size];
train_filename = data["filename"][:train_size];

test_data = data["data"][train_size:];
test_category = data["category"][train_size:];
test_filename = data["filename"][train_size:];



num_labels = 5
vocab_size = 5000
batch_size = 100

tokenizer = TfidfVectorizer(analyzer='char', token_pattern=r'\w{1,}', ngram_range=(2,3), max_features=vocab_size);
tokenizer.fit(train_data);


x_train = tokenizer.transform(train_data);
x_test = tokenizer.transform(test_data);

encoder = LabelBinarizer()
encoder.fit(train_category)
y_train = encoder.transform(train_category)
y_test = encoder.transform(test_category)




#build model

model = Sequential()
model.add(Dense(512, input_shape=(vocab_size,)))
model.add(Activation('relu'))
model.add(Dropout(0.3))
model.add(Dense(512))
model.add(Activation('relu'))
model.add(Dropout(0.3))
model.add(Dense(num_labels))
model.add(Activation('softmax'))
model.summary()

model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy'])

history = model.fit(x_train, y_train,
                    batch_size=batch_size,
                    epochs=1,
                    verbose=1,
                    validation_split=0.1)


score = model.evaluate(x_test, y_test,
                       batch_size=batch_size, verbose=1)

print('Test accuracy:', score[1])

text_labels = encoder.classes_

for i in range(5):
    prediction = model.predict(np.transpose(x_test[i]))
    predicted_label = text_labels[np.argmax(prediction[0])]
    print(test_filename.iloc[i])
    print('Actual label:' + test_category.iloc[i])
    print("Predicted label: " + predicted_label)
    
print('Test accuracy:', score[1])    
print("End")

