from faster_whisper import WhisperModel
from datetime import datetime
import psutil
import os

FILE_PATH = '/root/Code/demos/python/whisper/audio/time_es/'

def transcribe_file(file): 
    file_name = FILE_PATH + file
    start_time = datetime.now()
    model = WhisperModel('medium', device="cpu", compute_type="float32")
    print("File: %s" % file)
    segments, info = model.transcribe(file_name, beam_size=5, language='es')

    mem_showed = False
    for segment in segments:
        if (mem_showed == False):
            print("Memory: %.4fGB" % (psutil.Process(os.getpid()).memory_info().rss / 1024 / 1024 / 1024))
            mem_showed = True
        # print("[%.2fs -> %.2fs] %s" % (segment.start, segment.end, segment.text))

    print("Time: %ss" % (datetime.now() - start_time))


if __name__ == "__main__":
    for file in os.listdir(FILE_PATH):
        if file.endswith("mp3"):
            transcribe_file(file=file)
            print('\n')