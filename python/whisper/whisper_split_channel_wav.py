from scipy.io import wavfile
from pydub import AudioSegment
import whisper
from datetime import datetime
import os


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
    print("File: ", mp3_file)

    # init file and model
    wav_file = mp3_to_wav(mp3_file)
    left_channel, right_channel = split_channel(wav_file)
    model = whisper.load_model("medium")

    # recognize left channel
    start_time = datetime.now()
    left_result = model.transcribe(left_channel, fp16=False, language='es')
    print("Left channel recognize time:", datetime.now() - start_time)

    # recognize right channel
    start_time = datetime.now()
    right_result = model.transcribe(right_channel, fp16=False, language='es')
    print("Right channel recognize time:", datetime.now() - start_time)

    # merge channels and sort segments
    for s in left_result['segments']:
        s['channel'] = '催员'
    for s in right_result['segments']:
        s['channel'] = '客户'
    all_segments = left_result['segments'] + right_result['segments']
    all_segments.sort(key=lambda s: s['end'])

    # output merged result
    print_segments(all_segments)

    # remove temp files
    os.remove(wav_file)
    os.remove(left_channel)
    os.remove(right_channel)


def print_segments(segments):
    for s in segments:
        print('[{}]{:0>5.2f}-{:0>5.2f}: {}'.format(s['channel'], int(s['start']), int(s['end']), s['text']))


if __name__ == "__main__":
    FILE_PATH = '/Users/ravooo/Downloads/spanish/'
    for file in os.listdir(FILE_PATH):
        if file.endswith("wav"):
            audio_to_text(file)
            print('\n')
