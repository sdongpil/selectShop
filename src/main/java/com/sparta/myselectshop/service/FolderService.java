package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FolderService {
    private final FolderRepository folderRepository;

    public void addFolder(List<String> folderNames, User user) {

        List<Folder> existFolder = folderRepository.findAllByUserAndNameIn(user, folderNames);

        List<Folder> folderList = new ArrayList<>();

        for (String folderName : folderNames) {
            if (!isExistFolderName(folderName, existFolder)) {

                Folder folder = new Folder(folderName, user);

                folderList.add(folder);
            } else {
                throw new IllegalArgumentException("폴더명 중복");
            }
        }
        folderRepository.saveAll(folderList);

    }

    public void addFolderV2(List<String> folderNames, User user) {

        List<Folder> folderList = folderNames.stream()
                .map(f -> new Folder(f, user))
                .toList();

        folderRepository.saveAll(folderList);
    }

    private boolean isExistFolderName(String folderNames, List<Folder> existFolder) {
        for (Folder folder : existFolder) {
            if (folder.getName().equals(folderNames)) {

                log.info("exist folder ={}", folder.getName());
                log.info("folderNames ={}", folderNames);


                return true;
            }
        }
        return false;
    }

    public List<FolderResponseDto> getFolders(User user) {

      List<Folder> folderList =  folderRepository.findAllByUser(user);

        return folderList.stream()
                .map(FolderResponseDto::new)
                .collect(Collectors.toList());
    }
}
