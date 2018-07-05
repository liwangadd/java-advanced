package cn.windylee.findaelement;

import com.google.common.collect.Iterables;

import java.util.List;
import java.util.ListIterator;

public class FindElementInAList<T> {

    public T findUsingIndexOf(T element, List<T> list){
        int index = list.indexOf(element);
        if(index>0){
            return element;
        }
        return null;
    }

    public boolean findUsingListIterator(T element, List<T> list){
        ListIterator<T> listIterator = list.listIterator();
        while(listIterator.hasNext()){
            T elementFromList = listIterator.next();
            if(elementFromList.equals(element)){
                return true;
            }
        }
        return false;
    }

    public boolean findUsingEnhanceForLoop(T element, List<T> list){
        for(T elementFromList:list){
            if(elementFromList.equals(element)){
                return true;
            }
        }
        return false;
    }

    public T findUsingStream(T element, List<T> list){
        return list.stream().filter(ele->ele.equals(element))
                .findFirst().orElse(null);
    }

    public T findUsingParallelStream(T element, List<T> list){
        return list.parallelStream().filter(ele->ele.equals(element)).findAny().orElse(null);
    }

    public T findUsingGuava(T element, List<T> list){
        return Iterables.tryFind(list, element::equals).orNull();
    }

}
