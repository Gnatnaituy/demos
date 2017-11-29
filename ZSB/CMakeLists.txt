cmake_minimum_required(VERSION 3.8)
project(ZSB)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES main.c fun_result.c fun_fill.c fun_quiz.c)
add_executable(ZSB ${SOURCE_FILES})