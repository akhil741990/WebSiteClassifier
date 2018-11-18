import sys
import pandas as pd
import numpy as np
import pickle
from keras.preprocessing.text import Tokenizer
from keras.models import Sequential
from keras.models import load_model
from keras.layers import Activation, Dense, Dropout
from sklearn.preprocessing import LabelBinarizer
import sklearn.datasets as skds
from pathlib import Path



vocab_size = 15000
data_tags = ["filename","category","data"]

data_list = []
text = Path('motor-cycle-sample.txt').read_text()
data_list.append(('','',text))

data = pd.DataFrame.from_records(data_list, columns=data_tags)  

tokenizer = Tokenizer(num_words=vocab_size);
tokenizer.fit_on_texts(data["data"]);

x_text = tokenizer.texts_to_matrix(data["data"], mode='tfidf');
model = load_model('./model.textclassifier')

text_labels = ['electronics', 'medical', 'motorcycle', 'space', 'sports']

prediction = model.predict(np.array([x_text[0]]))
predicted_label = text_labels[np.argmax(prediction[0])]
print("Predicted label: " + predicted_label)


print('Model loaded successfully:');