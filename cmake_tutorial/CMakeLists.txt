cmake_minimum_required(VERSION 3.10 FATAL_ERROR)
set(CMAKE_LEGACY_CYGWIN_WIN32 0)

project("To Do List")

list(APPEND CMAKE_MODULE_PATH ${CMAKE_SOURCE_DIR}/cmake/Modules)

enable_testing()
include(gmock)

find_package(Boost 1.32 REQUIRED COMPONENTS program_options)
include_directories(SYSTEM ${Boost_INCLUDE_DIRS})

if ("${CMAKE_CXX_COMPILER_ID}" STREQUAL "GNU" OR
        "${CMAKE_CXX_COMPILER_ID}" STREQUAL "Clang")
    set(warnings "-Wall -Wextra -Werror")
elseif ("${CMAKE_CXX_COMPILER_ID}" STREQUAL "MSVC")
  set(warnings "/W4 /wd4512 /WX /EHsc")
  # Disabled Warnings:
  #   4512 "assignment operator could not be generated"
  #        This warning provides no useful information and will occur in
  #        well formed programs.
  #        <http://msdn.microsoft.com/en-us/library/hsyx7kbz.aspx>
endif ()
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${warnings}")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${warnings}")

if (NOT CONFIGURED_ONCE)
    set(CMAKE_CXX_FLAGS "${warnings}"
            CACHE STRING "Flags used by the compiler during all build types." FORCE)
    set(CMAKE_C_FLAGS "${warnings}"
            CACHE STRING "Flags used by the compiler during all build types." FORCE)
endif ()

include_directories(${CMAKE_CURRENT_SOURCE_DIR})

add_subdirectory(ToDoCore)

add_executable(toDo main.cc)
target_link_libraries(toDo toDoCore ${Boost_LIBRARIES})

add_test(toDoTest toDo)

set(CONFIGURED_ONCE TRUE CACHE INTERNAL
        "A flag showing that CMake has configured at least once.")