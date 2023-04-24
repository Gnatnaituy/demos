from scipy.io import wavfile

# 读取原始音频文件
sample_rate, data = wavfile.read('/Users/ravooo/Downloads/Chinese_28s.wav')

# 拆分左右声道
left_channel = data[:, 0]
right_channel = data[:, 1]

# 写入左声道文件
wavfile.write('/Users/ravooo/Downloads/left.wav', sample_rate, left_channel)

# 写入右声道文件
wavfile.write('/Users/ravooo/Downloads/right.wav', sample_rate, right_channel)