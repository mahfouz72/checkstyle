/*
AnnotationLocation
allowSamelineMultipleAnnotations = (default)false
allowSamelineSingleParameterlessAnnotation = (default)true
allowSamelineParameterizedAnnotation = (default)false
tokens = (default)CLASS_DEF, INTERFACE_DEF, PACKAGE_DEF, ENUM_CONSTANT_DEF, \
         ENUM_DEF, METHOD_DEF, CTOR_DEF, VARIABLE_DEF, RECORD_DEF, COMPACT_CTOR_DEF


*/

package com.puppycrawl.tools.checkstyle.checks.annotation.annotationlocation;

/* Config: default */


@MyAnnotation6
@MyAnnotation5
class InputAnnotationLocationCorrect
{

    @MyAnnotation6
    @MyAnnotation5
    public int a;

    @MyAnnotation5
    public int b;

    @MyAnnotation6
    @MyAnnotation5
    public int c;

    @MyAnnotation5
    public int d;

    @MyAnnotation6
    @MyAnnotation5
    public InputAnnotationLocationCorrect()
    {
        //comment
    }
    @MyAnnotation5
    @MyAnnotation6
    void foo1() {}

    @MyAnnotation5
    @MyAnnotation6
    void foo2() {}

    @MyAnnotation5
    @MyAnnotation6
    @MyAnnotation3
    @MyAnnotation4
    class InnerClass
    {
        @MyAnnotation6
        @MyAnnotation5
        public int a;

        @MyAnnotation5
        public int b;

        @MyAnnotation6
        @MyAnnotation5
        public int c;

        @MyAnnotation5
        public int d;

        @MyAnnotation6
        public InnerClass()
        {
            // comment
        }
        @MyAnnotation5
        @MyAnnotation6 void foo1(int a) {}

        @MyAnnotation5
        @MyAnnotation6
        void foo2() {}
    }

    @MyAnnotation5
    @MyAnnotation6
    InnerClass anon = new InnerClass()
    {
        @MyAnnotation6
        @MyAnnotation5
        public int a;

        @MyAnnotation5 public int b;

        @MyAnnotation6
        @MyAnnotation5
        public int c;

        @MyAnnotation5
        public int d;

        @MyAnnotation5
        @MyAnnotation6 void foo1() {}

        @MyAnnotation5
        @MyAnnotation6
        void foo2() {}
    };

}

@MyAnnotation5
@MyAnnotation6
class _Foo {}

@interface MyAnnotation5 {}

@interface MyAnnotation6 {}
