nome=$1
matricula=$2
cd ~/.iCall/icall-libFP

gksudo sh ./lib/libfprint-master/examples/enroll
mkdir -p  ~/.icall/$matricula

sudo cp -p  ~/.fprint/prints/0001/00000000/7 ~/.icall/$matricula && echo $matricula"|"$nome >> ~/iCall/enroll.icall
sleep 3s 

