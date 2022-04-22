# greendeckformsubmission

END POINTS -  SCREENSHOTS PERTAINING TO IT HAVE BEEN GIVEN IN THE FOLDER 'SCREENSHOTS OF POSTMAN'

A. /createaccount -             This is a postmapping request end point where the account will be created and email verification will be done with the help of event
                                package contained in the package explorer,which would help generating the token url. 


B./login -                      Spring security has been used here.Therefore, spring will  not allow us to enter any endpoint without credentials unless we have allowed 
                                a certain endpoint to by pass authorization. By-pass authorization code will be found in 'config' package in the package explorer.
                                 user- 'amrat' , password- 'password'.

C. /getfollowers/{id}-          It is a getmapping request where we will be able to find the followers and following of a particular user id along with all their details
                                namely- userid,username,name,email. Screenshots of successful testing of get/followers/{userid} (where userid- 1,2,3) have been 
                                attached to the 'screenshots of postman'  folder.

D./you-may-know/{id}-           A getmapping request where a user will be able to see the followers of their followers which they are not yet following.
                                example= userid {1,2,3}... 1 follows 2   ,     3 follows 2   but   1 does not follow 3 ,then 1 will get 3 as people he may want to conne                                 with. 
