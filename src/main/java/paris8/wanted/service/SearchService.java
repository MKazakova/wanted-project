package paris8.wanted.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paris8.wanted.domain.Advertise;
import paris8.wanted.domain.CV;
import paris8.wanted.domain.Language;
import paris8.wanted.domain.Search;
import paris8.wanted.domain.UserRole;

@Service
public class SearchService {
	@Autowired
	private EntityManager entityManager;
	
	public List <CV> searchCorrespondingCV(Search search){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CV> criteriaQuery = criteriaBuilder.createQuery(CV.class);
		Root<CV> itemRoot = criteriaQuery.from(CV.class);
		criteriaQuery.where(createPredicates(itemRoot, criteriaBuilder, search, UserRole.HH));
        return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	public List <Advertise> searchCorrespondingAd(Search search){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Advertise> criteriaQuery = criteriaBuilder.createQuery(Advertise.class);
		Root<Advertise> itemRoot = criteriaQuery.from(Advertise.class);
		criteriaQuery.where(createPredicates(itemRoot, criteriaBuilder, search, UserRole.JS));
        return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	public List <CV> searchAuthoCorrespondingCV(Search search){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CV> criteriaQuery = criteriaBuilder.createQuery(CV.class);
		Root<CV> itemRoot = criteriaQuery.from(CV.class);
		criteriaQuery.where(createAuthoPredicates(itemRoot, criteriaBuilder, search, UserRole.HH));
		List <CV> result = entityManager.createQuery(criteriaQuery).getResultList();
		List <CV> finalResult = result.stream().filter(cv -> {
			return !languageProblem(search.getLanguages(), cv.getLanguages());
		}).collect(Collectors.toList());
        return finalResult;
	}
	
	public List <Advertise> searchAuthoCorrespondingAd(Search search){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Advertise> criteriaQuery = criteriaBuilder.createQuery(Advertise.class);
		Root<Advertise> itemRoot = criteriaQuery.from(Advertise.class);
		criteriaQuery.where(createAuthoPredicates(itemRoot, criteriaBuilder, search, UserRole.JS));
		List <Advertise> result = entityManager.createQuery(criteriaQuery).getResultList();
		List <Advertise> finalResult = result.stream().filter(a -> {
			String [] askedCompetences = a.getCompetences().split(",");
			int absent = 0;
			for(String competence: askedCompetences) {
				String askedCompetence = competence.trim().toLowerCase();
				if(!search.getCompetences().toLowerCase().contains(askedCompetence.substring(0, askedCompetence.length()>2?askedCompetence.length()-2: askedCompetence.length()))) {
					absent++;
				}
			}
			return absent<=3&&!languageProblem(a.getLanguages(), search.getLanguages());
		}).collect(Collectors.toList());
		
		
        return finalResult;
	}
	
	private Predicate createAuthoPredicates(Root itemRoot, CriteriaBuilder criteriaBuilder,
			Search search, UserRole userRole) {
		Predicate finalPredicate = criteriaBuilder.like(itemRoot.get("position"), "%"+search.getPosition()+"%");
		if(search.getCity()!=null&& !search.getCity().equals("")) {
			finalPredicate = criteriaBuilder.and(finalPredicate, 
					criteriaBuilder.equal(itemRoot.get("city"), search.getCity()));
		}
		if(search.getCountry() != null && !search.getCountry().equals("")) {
			finalPredicate = criteriaBuilder.and(finalPredicate, 
					criteriaBuilder.equal(itemRoot.get("country"), search.getCountry()));
		}
		
		if (search.getCompetences()!=null && !search.getCompetences().equals("")) {
			String competences [] = search.getCompetences().split(",");
			if(userRole==UserRole.HH) {
				for(String competence : competences) {
					finalPredicate = criteriaBuilder.and(finalPredicate, 
						criteriaBuilder.like(criteriaBuilder.lower(itemRoot.get("competences")), "%" + competence.trim().toLowerCase() + "%"));
				}
			}
		}
		if(search.getSalary() != 0) {
			if(userRole==UserRole.JS) {
				finalPredicate = criteriaBuilder.and(finalPredicate, 
							criteriaBuilder.greaterThanOrEqualTo(itemRoot.get("maxSalary"), search.getSalary()));
			}
			else if(userRole==UserRole.HH) {
				finalPredicate = criteriaBuilder.and(finalPredicate, 
					criteriaBuilder.lessThanOrEqualTo(itemRoot.get("minSalary"), search.getSalary()));
			}	
		}
		
		if(search.getEducation() != -1) {
			if(userRole==UserRole.JS) {
				finalPredicate = criteriaBuilder.and(finalPredicate, 
						criteriaBuilder.or(criteriaBuilder.lessThanOrEqualTo(itemRoot.get("bacPlusLevel"), search.getEducation()),
								criteriaBuilder.isNull(itemRoot.get("bacPlusLevel")
								)));
			}
			else if(userRole==UserRole.HH) {
				finalPredicate = criteriaBuilder.and(finalPredicate, 
						criteriaBuilder.or(criteriaBuilder.greaterThanOrEqualTo(itemRoot.get("bacPlusLevel"), search.getEducation()),
								criteriaBuilder.isNull(itemRoot.get("bacPlusLevel")
								)));
			}
		}
		if(search.getExperience()!=-1) {
			if(userRole==UserRole.JS) {
				finalPredicate = criteriaBuilder.and(finalPredicate, 
						criteriaBuilder.lessThanOrEqualTo(itemRoot.get("monthsOfExperience"), search.getExperience()));
			}
			else if(userRole==UserRole.HH) {
				finalPredicate = criteriaBuilder.and(finalPredicate, 
					criteriaBuilder.greaterThanOrEqualTo(itemRoot.get("monthsOfExperience"), search.getExperience()));
			}
		}
		return finalPredicate;
	}

	public Predicate createPredicates(Root itemRoot, CriteriaBuilder criteriaBuilder, Search search, UserRole userRole) {
		Predicate finalPredicate = criteriaBuilder.like(itemRoot.get("position"), "%"+search.getPosition()+"%");
		if(search.getCity()!=null&& !search.getCity().equals("")) {
			finalPredicate = criteriaBuilder.and(finalPredicate, 
					criteriaBuilder.equal(itemRoot.get("city"), search.getCity()));
		}

		if(!search.isLargeSearch()) {
			return finalPredicate;
		}
		else {
			if(search.getCountry() != null && !search.getCountry().equals("")) {
				finalPredicate = criteriaBuilder.and(finalPredicate, 
						criteriaBuilder.equal(itemRoot.get("country"), search.getCountry()));
			}
			
			if (search.getCompetences()!=null && !search.getCompetences().equals("")) {
				String competences [] = search.getCompetences().split(",");
				for(String competence : competences) {
					finalPredicate = criteriaBuilder.and(finalPredicate, 
							criteriaBuilder.like(criteriaBuilder.lower(itemRoot.get("competences")), "%" + competence.trim().toLowerCase() + "%"));
				}
			}
			
			finalPredicate = specificPredicates(itemRoot, criteriaBuilder, userRole, finalPredicate, search);
		}
		
		return finalPredicate;
	}
	
	private Predicate specificPredicates(Root itemRoot, CriteriaBuilder criteriaBuilder, UserRole userRole, Predicate predicate, Search search) {
		if(search.getSalary() != 0) {
			if(userRole==UserRole.JS) {
				predicate = criteriaBuilder.and(predicate, 
							criteriaBuilder.greaterThanOrEqualTo(itemRoot.get("maxSalary"), search.getSalary()));
			}
			else if(userRole==UserRole.HH) {
				predicate = criteriaBuilder.and(predicate, 
					criteriaBuilder.lessThanOrEqualTo(itemRoot.get("minSalary"), search.getSalary()));
			}	
		}
		
		if(search.getEducation() != -1) {
			if(userRole==UserRole.JS) {
				predicate = criteriaBuilder.and(predicate, 
							criteriaBuilder.lessThanOrEqualTo(itemRoot.get("bacPlusLevel"), search.getEducation()));
			}
			else if(userRole==UserRole.HH) {
				predicate = criteriaBuilder.and(predicate, 
					criteriaBuilder.greaterThanOrEqualTo(itemRoot.get("bacPlusLevel"), search.getEducation()));
			}
		}
		
		if(search.getExperience()!=-1) {
			if(userRole==UserRole.JS) {
				switch(search.getExperience()){
					case 0: predicate = criteriaBuilder.and(predicate, 
							criteriaBuilder.lessThanOrEqualTo(itemRoot.get("monthsOfExperience"), 0));
							break;
					case 1: predicate = criteriaBuilder.and(predicate, 
							criteriaBuilder.between(itemRoot.get("monthsOfExperience"), 6, 24));
							break;
					case 2: predicate = criteriaBuilder.and(predicate, 
							criteriaBuilder.between(itemRoot.get("monthsOfExperience"), 24, 60));
							break;
					case 3: predicate = criteriaBuilder.and(predicate, 
							criteriaBuilder.between(itemRoot.get("monthsOfExperience"), 60, 108));
							break;
					case 4: predicate = criteriaBuilder.and(predicate, 
							criteriaBuilder.greaterThanOrEqualTo(itemRoot.get("monthsOfExperience"), 120));
							break;
				}
			}
			else if(userRole==UserRole.HH) {
				predicate = criteriaBuilder.and(predicate, 
					criteriaBuilder.greaterThanOrEqualTo(itemRoot.get("monthsOfExperience"), search.getExperience()));
			}
		}

		return predicate;	
	}
	
	private boolean languageProblem(Set<Language> neededLanguages, Set<Language> existingLanguages) {
		boolean isLanguageProblem = false;
		if(neededLanguages!=null && neededLanguages.size() != 0) {
			if(existingLanguages==null||existingLanguages.size()==0) {
				isLanguageProblem = true;
			}
			
			for(Language l: neededLanguages) {
				if(existingLanguages.stream().filter(el->el.getLanguage().equals(l.getLanguage()) && el.getLevel().ordinal()>=l.getLevel().ordinal()).collect(Collectors.toList()).size()==0) {
					isLanguageProblem = true;
				}
			}
		}
		return isLanguageProblem;
	}
	
}
