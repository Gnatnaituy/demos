import whisper
from datetime import datetime

model = whisper.load_model("medium")

# # load audio and pad/trim it to fit 30 seconds
# audio = whisper.load_audio("/Users/ravooo/Downloads/158103181.mp3")
# audio = whisper.pad_or_trim(audio)

# # make log-Mel spectrogram and move to the same device as the model
# mel = whisper.log_mel_spectrogram(audio).to(model.device)

# # detect the spoken language
# _, probs = model.detect_language(mel)
# print(f"Detected language: {max(probs, key=probs.get)}")

# # decode the audio
# options = whisper.DecodingOptions()
# result = whisper.decode(model, mel, options)

# # print the recognized text 
# print(result.text)

print(datetime.now())
result = model.transcribe("/Users/ravooo/Downloads/32385692.mp3")

print(result["text"])

print(datetime.now())