Local app name : UnitTest

This application shows How to perform local unit testing.

HLD(HIGH LEVEL DESIGN):
This has been implemented based on MVP architecture (model view presenter). 

Presenter:
Model consists of User.kt, Track.kt and PlayList.kt

User.kt:
This is data structure used to store the user specific information like user name, user id and pro value

Track.kt:
This class used to store the track related information like id, title and duration of track

PlayList.kt:
This is main presenter data structure used to store all information about tracks and corresponding user.
Once we started to add the track into play list, then we suppose get corresponding user info also. So This class having 
a field to store user object. So that from PlayList class we will get all info about the user like whether user is 
pro or not. If the user is pro then PlayList having capability to store up to 200 track at a time, else just 100.

And main this PlayList class using one specific data structure to store the tracks list. 
Here we are using linked hash set store the list of tracks, the reasons are given below
    
    1. Set is having property to avoid duplicate entry by default. No need of extra logic to write.
    2. Using linked hash set, we can make sure that entries are getting adding in the order of
    user selection. So we can play one by one by tracks based on the order which chose by user.
    3. Since linked has set is implemented using linked list, removing and adding of tracks is
    faster when compared to array list.

Method explanations:

1. addTrack()
Adding one track to play list by checking existing limit and if that track already not present in list

2. addTracks(tracks: List<Track>?)
Adding list of tracks and same time get restricting adding more than limit to tracks As well as checking uniqueness.

3. getNumberTask()
To get current number of task available

4. removeTask()
To remove specific task from the list

5. removeTasks()
To remove list tracks from play list

6. getTotalDuration()
To get total duration of tracks

7. isIdentical()
Checking identical and return true if list does not have that specific track



LLD(LOW LEVEL DESIGN)
1. Platform used:
Android 
2. Language: 
Kotlin
3. Library used:
4. DataStructure: 
linkedHashSet : Reason are mentioned above.


-> PlayListTest.kt
This is main class used to perform local unit testing. 
In this unit testing, intentionally made three assertion errors to make sure that business 
logicis working or not. Those are when
 
 1. When passing null track
 2. When passing more than 100 track where as the user is not a PRO
 3. When passing more than 200 track to add into play list where the user is PRO
 
Methods are usage ->
1. add_null_track_test_should_not_add_return_false():
During adding of new track to play list, track should not be null
This test case will fail as we trying to add null track. Through this test case, our aim is, 
user should not add any null track.

2. add_actual_single_track_return_true_if_success():
During adding of new track to play list, track should not be null

3. add_n_number_of_track():
Adding same n tracks

4. add_more_than_hundred_number_of_same_track():
Adding same 101 tracks
This test case will not fail as we are trying adding same tracks, so basically more than 100 same tracks,
among that only one will take and add to main play lis

5. add_more_than_hundred_number_of_unique_track():
Adding unique 101 tracks
This test case will fail as (it will show assertion error) as we trying to add unique set of
tracks which more than 100

6. get_current_number_of_tracks_in_play_list():
To get total number of tracks present in play list

7. get_total_track_duration():
Getting current total duration of play list, it should greater than zero as we have
added at least one track to list

8. adding_tracks_to_pro_user_less_than_200():
Adding upto 200 tracks for pro users

9. adding_tracks_to_pro_user_greater_than_200():
Trying to add more than 200 tracks to play list.
This will show assertion error since more than 200 tracks will not support to list even user is pro.

10. remove_random_track():
To remove the random task from the list


In order to work unit test, we need to add corresponding dependancy in build.gradle
testImplementation 'junit:junit:4.12'
