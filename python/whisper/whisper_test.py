from scipy.io import wavfile
from pydub import AudioSegment
import whisper
from datetime import datetime

def mp3_to_wav(input_file) -> str:
    wav_file = input_file[:-4] + '.wav'
    data = AudioSegment.from_mp3(input_file)
    data.export(wav_file, format='wav')

    return wav_file


def split_channel(file_name):
    sample_rate, data = wavfile.read(file_name)
    left_channel, right_channel = data[:, 0], data[:, 1]

    left_file = file_name[:-4] + '_left.wav'
    right_file = file_name[:-4] + '_right.wav'

    wavfile.write(left_file, sample_rate, left_channel)
    wavfile.write(right_file, sample_rate, right_channel)

    return left_file, right_file


def audio_to_text(mp3_file):
    print("Origin       File: ", mp3_file)

    wav_file = mp3_to_wav(mp3_file)
    print("Converted    File: ", wav_file)

    left_channel, right_channel = split_channel(wav_file)
    print("Splited      File: ", left_channel, right_channel)

    model = whisper.load_model("medium")
    
    start_time = datetime.now()
    print('')
    left_result = model.transcribe(left_channel, fp16=False, language='zh', initial_prompt='以下是普通话的句子')
    print_segments(left_result['segments'])
    print("Left channel recoginize finished, cost time:", datetime.now() - start_time)

    start_time = datetime.now()
    print('')
    right_result = model.transcribe(right_channel, fp16=False, language='zh', initial_prompt='以下是普通话的句子')
    print_segments(right_result['segments'])
    print("Right channel recoginize finished, cost time:", datetime.now() - start_time)


def print_segments(segments):
    for s in segments:
        print("{:.2f}".format(int(s['start'])), '-', "{:.2f}".format(int(s['end'])), ': ', s['text'])


if __name__ == "__main__":
    input_file = "/Users/ravooo/Downloads/8025264c-3e2d-4a34-acd3-a00855f88d78_zh_51.mp3"
    audio_to_text(input_file)
