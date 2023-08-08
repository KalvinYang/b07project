package com.b07project.application;

import com.google.firebase.database.DatabaseReference;

import java.util.Map;

abstract class ObjectsToSave {

    DatabaseReference ref;

    ObjectsToSave(Class cName) {
        ref = MainActivity.db.getReference(cName.getSimpleName());
    }

    ObjectsToSave() {
    }

    abstract Map<String, Object> createHashMap();

    public void updateObject(String key, Map<String, Object> map) {
        MainActivity.db.getReference().child("Status").setValue("Trying");
        ref.child(key).updateChildren(map);
    }
    public String saveObject(Map<String, Object> map) {
        String ref2 = ref.push().getKey();
        ref.child(ref2).setValue(map);
        return ref2;
    }

    //int updateNumber(CountDownLatch latch) {
    //        final int[] Num = new int[1];
    //        Query query = ref.child("Current");
    //        query.addListenerForSingleValueEvent(new ValueEventListener() {
    //            @Override
    //            public void onDataChange(@NonNull DataSnapshot snapshot) {
    //                Num[0] = Integer.parseInt(snapshot.getValue().toString());
    //                Num[0]++;
    //                ref.child("Current").setValue(Num[0]);
    //            }
    //
    //            @Override
    //            public void onCancelled(@NonNull DatabaseError error) {
    //                return;
    //            }
    //        });
    //        return Num[0];
    //    }
}
