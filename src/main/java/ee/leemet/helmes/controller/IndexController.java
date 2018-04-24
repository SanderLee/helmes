package ee.leemet.helmes.controller;

import ee.leemet.helmes.entity.SaveUserResponse;
import ee.leemet.helmes.entity.Sector;
import ee.leemet.helmes.entity.UserData;
import ee.leemet.helmes.service.SectorService;
import ee.leemet.helmes.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class IndexController {

	private SectorService sectorService;
	private UserDataService userDataService;

	@Autowired
	public IndexController(SectorService sectorService, UserDataService userDataService) {
		this.sectorService = sectorService;
		this.userDataService = userDataService;
	}

	@RequestMapping(value = "/saveUserData", method = RequestMethod.PUT)
	public SaveUserResponse saveUserData(UserData userData) {
		return userDataService.saveUserData(userData);
	}

	@RequestMapping("/getSectors")
	public Collection<Sector> getSectors() {
		return sectorService.getSectors();
	}
}
