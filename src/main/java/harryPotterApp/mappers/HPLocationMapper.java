package harryPotterApp.mappers;

import harryPotterApp.dto.HPLocationDto;
import harryPotterApp.entities.HPLocation;

public class HPLocationMapper {

    private HPLocationMapper() {
    }

    public static HPLocationDto mapToHPLocationDto(HPLocation hpLocation){
        return HPLocationDto.builder()
                .id(hpLocation.getId())
                .name(hpLocation.getLocationName())
                .residents(hpLocation.getResidents())
                .imagePath(hpLocation.getImagePath())
                .build();
    }
}
