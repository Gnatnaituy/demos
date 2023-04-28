from scipy.io import wavfile
from pydub import AudioSegment
import whisper
from datetime import datetime
import os

def mp3_to_wav(mp3_file) -> str:
    wav_file = mp3_file[:-4] + '.wav'
    data = AudioSegment.from_mp3(mp3_file)
    data.export(wav_file, format='wav')

    return wav_file


def split_channel(wav_file):
    sample_rate, data = wavfile.read(wav_file)
    left_channel, right_channel = data[:, 0], data[:, 1]

    left_file = wav_file[:-4] + '_left.wav'
    right_file = wav_file[:-4] + '_right.wav'

    wavfile.write(left_file, sample_rate, left_channel)
    wavfile.write(right_file, sample_rate, right_channel)

    os.remove(wav_file)

    return left_file, right_file


def audio_to_text(path: str, file: str):
    start = datetime.now()
    
    wav_file = mp3_to_wav(path + file)
    convert_time = datetime.now() - start


    left_channel, right_channel = split_channel(wav_file)
    split_time = datetime.now() - start - convert_time

    model = whisper.load_model("medium")
    left_result = model.transcribe(left_channel, fp16=False, language='zh', initial_prompt='以下是普通话的句子')
    right_result = model.transcribe(right_channel, fp16=False, language='zh', initial_prompt='以下是普通话的句子')
    transcribe_time = datetime.now() - start - convert_time - split_time
    
    print(file, '\t', convert_time, '\t', split_time, '\t', transcribe_time,'\t',  start, '\t', datetime.now())

    os.remove(left_channel)
    os.remove(right_channel)

def audio_to_text_not_zh(path: str, file: str):
    start = datetime.now()
    
    wav_file = mp3_to_wav(path + file)
    convert_time = datetime.now() - start


    left_channel, right_channel = split_channel(wav_file)
    split_time = datetime.now() - start - convert_time

    model = whisper.load_model("medium")
    left_result = model.transcribe(left_channel, fp16=False, language='es')
    right_result = model.transcribe(right_channel, fp16=False, language='es')
    transcribe_time = datetime.now() - start - convert_time - split_time
    
    print(file, '\t', convert_time, '\t', split_time, '\t', transcribe_time,'\t',  start, '\t', datetime.now())

    os.remove(left_channel)
    os.remove(right_channel)


if __name__ == "__main__":
    print('file\t\t', 'convert time(s)\t', 'split time(s)\t\t', 'transcribe time(s)\t', 'start at\t\t\t', 'end at')
    
    path = "/Users/ravooo/Downloads/speed_es/"
    files = sorted(os.listdir(path), key=lambda e : int(e[3:-4]))
    for file in files:
        audio_to_text(path, file)
    
