import React from 'react';
import { Music, Play, Smartphone, Github, Layers, ShieldCheck, Zap, Globe } from 'lucide-react';
import { motion } from 'motion/react';

export default function App() {
  return (
    <div className="min-h-screen bg-[#0D0D0D] text-white font-sans selection:bg-blue-600/30" dir="rtl">
      {/* Hero Section */}
      <nav className="p-6 flex justify-between items-center border-b border-white/5 backdrop-blur-md sticky top-0 z-50">
        <div className="flex items-center gap-2">
          <div className="w-10 h-10 bg-blue-600 rounded-xl flex items-center justify-center shadow-lg shadow-blue-600/20">
            <Music className="text-white w-6 h-6" />
          </div>
          <span className="text-xl font-bold tracking-tight">مشغل دزاي (Dazai Player)</span>
        </div>
        <div className="flex gap-6 text-sm font-medium text-gray-400">
          <a href="#features" className="hover:text-white transition-colors">المميزات</a>
          <a href="#architecture" className="hover:text-white transition-colors">البنية التقنية</a>
          <a href="#demo" className="hover:text-white transition-colors">معاينة</a>
        </div>
      </nav>

      <main>
        <section className="px-6 py-20 max-w-7xl mx-auto grid lg:grid-cols-2 gap-12 items-center">
          <motion.div 
            initial={{ opacity: 0, x: 20 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ duration: 0.6 }}
          >
            <h1 className="text-6xl font-extrabold tracking-tighter leading-none mb-6">
              تجربة <span className="text-blue-500">أندرويد</span> موسيقية فاخرة.
            </h1>
            <p className="text-xl text-gray-400 mb-8 max-w-lg leading-relaxed">
              مشغل دزاي هو محرك موسيقي بمستوى احترافي تم بناؤه باستخدام Kotlin و Media3 و Room. 
              مصمم لعشاق الصوت الذين يبحثون عن الدقة، وسلامة البيانات الوصفية، وجمالية "الوضع المظلم" الساحرة.
            </p>
            <div className="flex gap-4">
              <button className="bg-blue-600 hover:bg-blue-700 text-white px-8 py-3 rounded-full font-semibold transition-all shadow-lg shadow-blue-600/20 flex items-center gap-2">
                <Play className="w-4 h-4" /> تشغيل محلي
              </button>
              <button className="bg-white/5 hover:bg-white/10 text-white px-8 py-3 rounded-full font-semibold transition-all border border-white/10 flex items-center gap-2">
                <Github className="w-4 h-4" /> كود المصدر
              </button>
            </div>
          </motion.div>

          <motion.div 
            initial={{ opacity: 0, scale: 0.9 }}
            animate={{ opacity: 1, scale: 1 }}
            transition={{ duration: 0.8, delay: 0.2 }}
            className="hidden lg:block relative"
          >
            <div className="absolute -inset-0.5 bg-gradient-to-r from-blue-600 to-purple-600 rounded-3xl blur opacity-20 group-hover:opacity-40 transition duration-1000 group-hover:duration-200"></div>
            <div className="relative bg-[#1A1A1A] border border-white/10 rounded-3xl p-8 aspect-[9/16] shadow-2xl overflow-hidden">
               <div className="w-full h-full flex flex-col items-center justify-center text-center">
                  <div className="w-48 h-48 bg-gradient-to-br from-blue-600 to-indigo-900 rounded-2xl mb-8 shadow-2xl flex items-center justify-center">
                    <Music className="w-24 h-24 text-white/50" />
                  </div>
                  <h3 className="text-2xl font-bold mb-2">سوناتا ضوء القمر</h3>
                  <p className="text-gray-500">لودفيج فان بيتهوفن</p>
                  
                  <div className="mt-12 w-full space-y-4">
                    <div className="h-1 bg-white/10 rounded-full overflow-hidden">
                      <div className="h-full bg-blue-500 w-1/3 shadow-[0_0_10px_rgba(59,130,246,0.5)]"></div>
                    </div>
                    <div className="flex justify-between items-center px-4 flex-row-reverse">
                      <Zap className="w-5 h-5 text-gray-500" />
                      <Play className="w-10 h-10 text-white fill-white" />
                      <Layers className="w-5 h-5 text-gray-500" />
                    </div>
                  </div>
               </div>
               
               {/* Android UI Accents */}
               <div className="absolute bottom-4 left-1/2 -translate-x-1/2 w-24 h-1 bg-white/20 rounded-full"></div>
               <div className="absolute top-2 left-1/2 -translate-x-1/2 w-16 h-4 bg-black rounded-b-xl border border-white/5"></div>
            </div>
          </motion.div>
        </section>

        {/* Feature Grid */}
        <section id="features" className="bg-[#0A0A0A] py-24 px-6 border-y border-white/5">
          <div className="max-w-7xl mx-auto">
            <div className="mb-16">
              <h2 className="text-4xl font-bold mb-4">هندسة متقنة</h2>
              <p className="text-gray-400">التقنيات الأساسية التي تدعم مشغل دزاي.</p>
            </div>
            
            <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
              {[
                { icon: <ShieldCheck className="text-blue-500" />, title: "هوية دزاري (Dazai Soul)", desc: "تصميم سينمائي هادئ مستوحى من الأجواء الليلية والنيون الأزرق الخافت." },
                { icon: <Layers className="text-purple-500" />, title: "واجهة Ultra Smooth", desc: "تجربة مستخدم فائقة السلاسة بمعدل 120 إطاراً بحركات انسيابية ونعومة Spotify." },
                { icon: <Zap className="text-yellow-500" />, title: "محرك الصوت الاحترافي", desc: "دعم Media3 مع انتقال Crossfade وسلاسة تامة في التبديل بين المسارات." },
                { icon: <Globe className="text-green-500" />, title: "لغة وجدانية", desc: "دعم كامل للغة العربية بتعبيرات عميقة مثل 'عالمك الموسيقي' و 'يُعزف في وجدانك'." },
                { icon: <Smartphone className="text-pink-500" />, title: "تصميم Glassmorphism", desc: "تأثيرات زجاجية ضبابية وتوهج نيون ناعم يعكس الفخامة والهدوء." },
                { icon: <Play className="text-cyan-500" />, title: "أدوات متميزة", desc: "مصور صوتي (Visualizer) متفاعل مع نبض الموسيقى بجمالية الـ Minimal." }
              ].map((feature, i) => (
                <div key={i} className="p-8 bg-white/5 border border-white/10 rounded-3xl hover:bg-white/[0.07] transition-all group text-right">
                  <div className="w-12 h-12 bg-black rounded-xl flex items-center justify-center mb-6 border border-white/5 shadow-inner">
                    {feature.icon}
                  </div>
                  <h3 className="text-xl font-bold mb-3">{feature.title}</h3>
                  <p className="text-gray-400 leading-relaxed text-sm">
                    {feature.desc}
                  </p>
                </div>
              ))}
            </div>
          </div>
        </section>

        {/* Architecture Section */}
        <section id="architecture" className="py-24 px-6 max-w-7xl mx-auto">
          <div className="flex flex-col lg:flex-row gap-12 items-center">
             <div className="flex-1">
                <code className="text-blue-500 text-sm font-mono mb-4 block leading-none" dir="ltr">com.demonlab.lune.architecture</code>
                <h2 className="text-4xl font-bold mb-6">بُني من أجل <span className="italic">الاستقرار</span>.</h2>
                <div className="space-y-6">
                  <div className="flex gap-4">
                    <div className="mt-1 w-2 h-2 rounded-full bg-blue-500 shadow-[0_0_8px_rgba(59,130,246,1)]"></div>
                    <div>
                      <h4 className="font-bold mb-1">واجهة تعتمد على StateFlow</h4>
                      <p className="text-gray-400 text-sm">تدفق البيانات أحادي الاتجاه يضمن أن الواجهة تمثل دائمًا المصدر الحقيقي للبيانات.</p>
                    </div>
                  </div>
                  <div className="flex gap-4">
                    <div className="mt-1 w-2 h-2 rounded-full bg-purple-500 shadow-[0_0_8px_rgba(168,85,247,1)]"></div>
                    <div>
                      <h4 className="font-bold mb-1">عزل الرموز (MediaSession)</h4>
                      <p className="text-gray-400 text-sm">تواصل آمن للخدمة في الخلفية باستخدام بروتوكولات AndroidX Media3 القياسية.</p>
                    </div>
                  </div>
                  <div className="flex gap-4">
                    <div className="mt-1 w-2 h-2 rounded-full bg-green-500 shadow-[0_0_8px_rgba(34,197,94,1)]"></div>
                    <div>
                      <h4 className="font-bold mb-1">بساطة حقن التبعيات</h4>
                      <p className="text-gray-400 text-sm">أنماط حقن تبعيات خفيفة الوزن لأداء أفضل وتصحيح أخطاء أسهل.</p>
                    </div>
                  </div>
                </div>
             </div>
             
             <div className="flex-1 bg-black/50 border border-white/10 rounded-3xl p-8 font-mono text-xs text-blue-300/80 shadow-2xl relative overflow-hidden" dir="ltr">
                <div className="flex gap-2 mb-4">
                  <div className="w-3 h-3 rounded-full bg-red-500/50"></div>
                  <div className="w-3 h-3 rounded-full bg-yellow-500/50"></div>
                  <div className="w-3 h-3 rounded-full bg-green-500/50"></div>
                </div>
                <div className="space-y-1">
                  <p><span className="text-purple-400">package</span> com.demonlab.lune.playback</p>
                  <p className="text-gray-500">// Media3 initialization</p>
                  <p><span className="text-purple-400">class</span> PlaybackService : MediaSessionService() &#123;</p>
                  <p className="pl-4"><span className="text-purple-400">override fun</span> onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? &#123;</p>
                  <p className="pl-8 text-yellow-300">return mediaSession</p>
                  <p className="pl-4">&#125;</p>
                  <p>&#125;</p>
                  <p className="mt-4 text-gray-500">// Scoped playback control</p>
                  <p><span className="text-purple-400">fun</span> startSleepTimer(minutes: Int) &#123;</p>
                  <p className="pl-4">scope.launch &#123;</p>
                  <p className="pl-8">delay(minutes * <span className="text-orange-400">60000L</span>)</p>
                  <p className="pl-8">player.pause()</p>
                  <p className="pl-4">&#125;</p>
                  <p>&#125;</p>
                </div>
                <div className="absolute top-0 right-0 p-4 opacity-10">
                  <Music className="w-32 h-32" />
                </div>
             </div>
          </div>
        </section>
      </main>

      <footer className="p-12 border-t border-white/5 text-center text-gray-500 text-sm">
        <p>© 2026 مشروع مشغل دزاي. مرخص بموجب Apache 2.0.</p>
        <p className="mt-2 text-gray-700 italic">بُني للروح، وهُندس للجهاز.</p>
      </footer>
    </div>
  );
}


