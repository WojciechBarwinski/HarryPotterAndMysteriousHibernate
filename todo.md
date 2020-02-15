##1. To Change
####ShowCharacterController:
    - delete factory
    - doPost in doGet
    
####HpCharacterServiceImpl:
    - static method with privet constructor
    - prepareCharacterToView(Long id) - od if() to student and position List
    - "List<HPCharacterDto> allCharacters" as variable in controller where its use more then 1
    DONE - in view, as house use house name
    - handl exeption when delete character what was deleted in another web
    
####PetServiceImpl:
    - static method with privet constructor
    
####HomeController
    - delete unnecessary code
      
####ValidationService
     - refactoring: one method to each input data type (one for name, one for lastName etc)
     - all pattern as property
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
    
    