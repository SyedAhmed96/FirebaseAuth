/**
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.ahmed.firebaseauth;

public class Users {

    private String name;
    private String uid;
    private String photoUrl;
    private String UserUID;

    public Users() {
    }

    //For uid & Uid
    public Users(String name, String uid) {
        this.name = name;
        this.uid = uid;
         }

    //with Firebase ID
    public Users(String name, String uid, Object UserUID) {
        this.name = name;
        this.uid = uid;
        this.UserUID = (String) UserUID;
    }


    public Users(String name, String uid, String photoUrl) {
        this.name = name;
        this.uid = uid;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
