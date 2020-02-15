##1. To Change
####ShowCharacterController:
    DONE - delete factory
    DONE - doPost in doGet
    
####HpCharacterServiceImpl:
    - prepareCharacterToView(Long id) - od if() to student and position List
    - "List<HPCharacterDto> allCharacters" as variable in controller where its use more then 1
    DONE - in view, as house use house name
    - handl exeption when delete character what was deleted in another web
    
####HomeController
    DONE - delete unnecessary code
      
####ValidationService
     DONE - all pattern as property
####SingletonEntityManagerFactory
    - refactor and rename, it's not singleton  
    
##___________________
##2. Test  
#####HpCharacterService:
    - prepareCharacterToView(Long id)
    
#####All entities delete test with check another date
    
#####ValidationService:
    test all small validator 
    
###3. New feat

#####Character
    when create character, check name and lastName and block create when character is in DB
    option to find character by firstName and/or(?) lastName
    in view show character's all item
    option to add/delete imagePath in view with checkbutton "Yes, this is ${character.name} photo" and correct regex
    update character, for now: firstName, lastName, birthDate
    
#####Home
    add form "write Your name" and take it as cookies
    add in main bar "nameFromCookie" hello or something like that
    add error page in web.xml
    Read me on git folder and on main web
    
#####Another
    login
    register
    add option to filtr data in view ASD/DCS
    add "next page" of data
    add view only for example 10 position in table
    
    