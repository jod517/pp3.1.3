package testgroup.filmography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }
}
